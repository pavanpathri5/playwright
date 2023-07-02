package com.org.common.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Environment {

    public String readProperties(String key)
    {
        return readProperties(Constants._ENV_PROPERTY, key);
    }
    public String readProperties(String profileName, String key){
        Properties prop = new Properties();
        try(InputStream input = Files.newInputStream(Paths.get(profileName))){
            // load a env.properties file
            prop.load(input);
            // get the property value and print it out
            return prop.getProperty(key);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}
