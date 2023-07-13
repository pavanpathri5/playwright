package com.org.platform.app.core.applications;

import com.org.common.io.XmlLib;
import com.org.common.utils.Logger;
import com.org.platform.app.core.devices.Device;

public class AJIOApp extends Device {
    public App launchApplication(int port) throws Exception
    {
        XmlLib lib = new XmlLib();
        Logger.log("Launching AJIO app");
        App app = super.launchApplication(port, lib.readDeviceConfig("ajio"));
        return app;
    }
}
