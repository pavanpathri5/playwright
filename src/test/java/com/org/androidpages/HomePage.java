package com.org.androidpages;

import com.org.common.utils.Logger;
import com.org.platform.app.core.devices.DeviceInteract;
import com.org.platform.app.core.sys.DeviceElement;

public class HomePage {
    public DeviceInteract driver;
    public DeviceElement allowLocation;
    public HomePage(DeviceInteract driver) {
        this.driver = driver;
        allowLocation = new DeviceElement(driver, "id",
                "com.android.permissioncontroller:id/permission_allow_button");
    }

    public void allowLocation(){
        Logger.log("Click on Allow Location");
        allowLocation.tap();
    }
    }
