package com.org.common.io;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.org.common.config.Constants;
import com.org.common.config.Environment;
import com.org.common.utils.RandomData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlLib {

    public NodeList parseXML(String filepath, String nodename) throws ParserConfigurationException, SAXException, IOException
    {
        FileLib fileHandler = new FileLib();
        if(fileHandler.isExists(filepath) == false)
        {
            return null;
        }
        File fXmlFile = new File(filepath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList listOfNodes = doc.getElementsByTagName(nodename);
        return listOfNodes;
    }

    public void getTestCaseData(String filepath, Object currentInstance)
    {

        String fullpath = currentInstance.getClass().getCanonicalName();
        String methods = currentInstance.toString();
        System.out.println(methods);
        System.out.println(fullpath);
        System.out.println("=====");
        String apiname = fullpath.split("\\.")[4].trim();
        String datafile = String.format("resources/testData/backendapi/%s/testdata.xml", apiname);
        FileLib file = new FileLib();
        file.isExists(datafile);
        try
        {
            NodeList list = parseXML(datafile, "testcase");
            for(int i=0; i<list.getLength(); i++)
            {
                //Node node = list.item(i);
                //if(node.getAttributes().getNamedItem("name"))
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    private Element findNode(NodeList list, String attribute, String value)
    {
        //System.out.println(" >> attribute : " + attribute);
        //System.out.println(" >> value : " + value);

        for(int i=0; i<list.getLength(); i++)
        {
            Element el = (Element)list.item(i);
            if(value.equalsIgnoreCase(el.getAttribute(attribute)))
            {
                return el;
            }
        }
        return null;
    }

    private boolean isEmpty(NodeList list)
    {
        if(list == null)
        {
            return true;
        }
        else
        {
            if(list.getLength() == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }


    private Element getTillEnv(String category, String environment,String filename) throws ParserConfigurationException, SAXException, IOException
    {
        NodeList list = parseXML(filename, "api");
        if(isEmpty(list) == true)
        {
            System.out.println("Database dbConnectionPrefix not parsed or not found");
            throw new ParserConfigurationException("Database dbConnectionPrefix not parsed or not found");
        }

        Element apiEl = findNode(list, "category", category);

        NodeList connList = apiEl.getElementsByTagName("connection");
        if(isEmpty(connList) == true)
        {
            System.out.println("Database connection not parsed or not found");
            throw new ParserConfigurationException("Database connection not parsed or not found");
        }

        Element connectionEl = findNode(connList, "env", environment);
        return connectionEl;
    }
    public String getWebConfigData(String portalName, String nodeName) throws Exception
    {
        String filePath = Constants._WEB_PORTAL_CONFIG;
        NodeList listOfPortals = parseXML(filePath, "portal");
        Element el = findNode(listOfPortals, "value", portalName.trim().toLowerCase());
        NodeList envList = el.getElementsByTagName("env");
        Element finalEl = findNode(envList, "value", new Environment().readProperties("env"));
        return finalEl.getElementsByTagName(nodeName.trim()).item(0).getTextContent();
    }

    public HashMap<String, Object> readDeviceConfig(String deviceCategory)
    {
        String filePath = Constants._DEVICE_CONFIG;
        NodeList list;
        try
        {
            list = parseXML(filePath, "device");
            Element deviceElemenet = findNode(list, "type", deviceCategory);

            NodeList elementProperty = deviceElemenet.getElementsByTagName("capability");
            HashMap<String, Object> map = new HashMap<>();
            for(int i=0; i<elementProperty.getLength(); i++)
            {
                Element el = (Element)elementProperty.item(i);
                String key = el.getAttribute("key");
                String value = (new RandomData()).mergeWithEnvironment(el.getAttribute("value")); // TODO: Convert

                switch(el.getAttribute("type").trim().toLowerCase())
                {
                    case "string": map.put(key, value); break;
                    case "integer": map.put(key, Integer.parseInt(value)); break;
                    case "boolean": map.put(key, Boolean.parseBoolean(value)); break;
                }
            }
            return map;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
