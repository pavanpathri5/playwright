package com.org.platform.app.core.applications;

import com.org.platform.app.core.devices.DeviceInteract;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class App {
    public DeviceInteract instance = null;
    public App(DeviceInteract driver)
    {
        instance = driver;
    }

    public void stop()
    {
        // Stop the driver
        try
        {
            instance.driver.quit();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
