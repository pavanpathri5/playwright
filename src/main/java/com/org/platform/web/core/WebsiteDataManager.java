package com.org.platform.web.core;


import com.org.common.io.XmlLib;

public class WebsiteDataManager{
    private String name;
    public WebsiteDataManager(String name)
    {
        this.name = name;
    }

    public String getValue(String nodeName)
    {
        XmlLib xml = new XmlLib();
        try
        {
            return xml.getWebConfigData(name, nodeName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public String getURL()
    {
        XmlLib xml = new XmlLib();
        try
        {
            return xml.getWebConfigData(name, "url");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserName()
    {
        XmlLib xml = new XmlLib();
        try
        {
            return xml.getWebConfigData(name, "username");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserName(String key)
    {
        XmlLib xml = new XmlLib();
        try
        {
            return xml.getWebConfigData(name, key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String getPassword()
    {
        XmlLib xml = new XmlLib();
        try
        {
            return xml.getWebConfigData(name, "password");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String getPassword(String key)
    {
        XmlLib xml = new XmlLib();
        try
        {
            return xml.getWebConfigData(name, key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}

