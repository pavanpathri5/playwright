package com.org.platform.app.core.applications;

import com.org.common.utils.Logger;
import com.org.platform.app.core.devices.AppiumInteract;

public class Appium {
    public AppiumInteract appiuminstance;
    public int port;
    public Appium(AppiumInteract service,int port)
    {
        appiuminstance = service;
        this.port=port;
    }

    public void start(){
        Logger.log("start APPIUM SERVER");
        appiuminstance.service.start();
    }
    public void stop()
    {
        // Destroy the process
        try
        {
            Logger.log("Stopping Appium Server");
            appiuminstance.service.stop();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
