package com.org.platform.app.core.devices;


import com.org.common.utils.Logger;
import com.org.platform.app.core.applications.App;
import com.org.platform.app.core.applications.Appium;
import com.org.platform.app.core.sys.DeviceAutomation;
import com.org.platform.app.core.sys.SystemInteract;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

public class Device {
    public App launchApplication(int port, HashMap<String, Object> capabilities) throws MalformedURLException
    {
        DeviceAutomation deviceAuto = new DeviceAutomation();
        DeviceInteract driver = deviceAuto.createDriver(port, capabilities);
        return new App(driver);
    }

    public Appium runServiceAppium() throws IOException
    {
        int port = (new SystemInteract()).getFreePort();
        Logger.log("Starting APPIUM SERVER with PORT = " + port);
        AppiumInteract interact=new AppiumInteract();
        interact.service=runAppium(port);
        return new Appium(interact,port);
    }

    public static AppiumDriverLocalService runAppium(int port){
        Logger.log("Running appium server");
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1"); // Set the IP address of the machine running the Appium server
        builder.usingPort(port); // Set the port number
        // Start the Appium server
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        return service;
    }

}
