package com.org.common.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class FileLib
{
    public boolean isExists(String filepath)
    {
        File file = new File(filepath);
        if(file.exists() == false)
        {
            System.out.println("FILE DOES NOT EXISTS: " + filepath);
            return false;
        }
        else
        {
            System.out.println("FILE FOUND: " + filepath);
            return true;
        }
    }

    public List<String> getFileContentAsList(String filepath)
    {
        // Check existence of file
        if(isExists(filepath) == false)
        {
            return null;
        }

        BufferedReader br = null;
        FileReader fr = null;
        List<String> lines = new ArrayList<String>();

        // Read the file
        try
        {
            fr = new FileReader(filepath);
            br = new BufferedReader(fr);
            String line = br.readLine();

            while ((line = br.readLine()) != null)
            {
                lines.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // Close the open connections
            try
            {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return lines;
    }

    public boolean writeToFile(String filepath, String content, boolean shallAppend)
    {
        BufferedWriter bw = null;
        FileWriter fw = null;
        new File (filepath);
        try
        {
            fw = new FileWriter(filepath, shallAppend);
            bw = new BufferedWriter(fw);
            bw.write(content);
            return true;

        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void downloadFile(String url, String saveFilePath) throws Exception
    {
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(saveFilePath);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

    public String getFileContentAsString(String filepath)
    {
        // Check existence of file
        if(isExists(filepath) == false)
        {
            System.out.println("file not found");
            return null;
        }

        BufferedReader br = null;
        FileReader fr = null;
        String lines = "";

        // Read the file
        try
        {
            fr = new FileReader(filepath);
            br = new BufferedReader(fr);
            String line = ""; //br.readLine();

            while ((line = br.readLine()) != null)
            {
                lines = lines + System.lineSeparator() + line;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // Close the open connections
            try
            {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return lines.trim();
    }
}
