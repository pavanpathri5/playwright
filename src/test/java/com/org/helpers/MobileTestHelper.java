package com.org.helpers;

import com.org.platform.app.core.applications.AJIOApp;
import com.org.platform.app.core.applications.App;
import com.org.platform.app.core.applications.Appium;
import lombok.SneakyThrows;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class MobileTestHelper {
    public AJIOApp ajioapp=new AJIOApp();
    public Appium appium;
    public App app;

    @SneakyThrows
    @BeforeClass
    public void setup(){
        appium=ajioapp.runServiceAppium();
        appium.start();
    }

    @BeforeMethod
    @SneakyThrows
    public void launch(){
        app=ajioapp.launchApplication(appium.port);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        appium.stop();
    }
}
