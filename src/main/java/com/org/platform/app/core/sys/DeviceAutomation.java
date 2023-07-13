package com.org.platform.app.core.sys;

import com.org.platform.app.core.devices.DeviceInteract;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DeviceAutomation {
    public DeviceInteract createDriver(int port, HashMap<String, Object> capabilities) throws MalformedURLException
    {
        DesiredCapabilities capability = new DesiredCapabilities();

        for (Map.Entry<String, Object> entry : capabilities.entrySet())
        {
            String key = entry.getKey();

            Object value = entry.getValue();

            System.out.println(key + " " + value);

            switch(value.getClass().getSimpleName())
            {
                case "String": 	capability.setCapability(key, (String)value); 	break;
                case "Integer": 	capability.setCapability(key, (Integer)value); 	break;
                case "Boolean": 	capability.setCapability(key, (Boolean)value); 	break;
            }
        }

        AppiumDriver driver = null;
        try
        {
            driver = new AppiumDriver(new URL("http://127.0.0.1:" + port), capability);
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION::");
            System.out.println(e);
        }
        DeviceInteract deviceInteract = new DeviceInteract();
        deviceInteract.driver = driver;

        return deviceInteract;
    }
}
