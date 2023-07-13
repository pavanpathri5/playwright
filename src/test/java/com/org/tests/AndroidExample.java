package com.org.tests;

import com.beust.ah.A;
import com.org.common.utils.GenericDataProvider;
import com.org.helpers.MobileTestHelper;
import com.org.platform.app.core.applications.AJIOApp;
import com.org.platform.app.core.applications.App;
import com.org.platform.app.core.applications.Appium;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AndroidExample extends MobileTestHelper {
    private AJIOApp ajioapp=new AJIOApp();
    Appium appium;
    @SneakyThrows
    @Test(priority = 1,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testOne(JSONObject testdata){
        appium=ajioapp.runServiceAppium();
        appium.start();
        App app=ajioapp.launchApplication(appium.port);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        appium.stop();
    }
}
