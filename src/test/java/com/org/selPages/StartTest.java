package com.org.selPages;

import com.org.common.utils.Logger;
import org.openqa.selenium.WebDriver;
import com.org.testdata.service.GetTestData;

public class StartTest {
    WebDriver driver;
    public StartTest(WebDriver driver){
        Logger.log("Initiating Driver");
        this.driver=driver;
    }

    public void openUrl(){
        GetTestData gettestdata=new GetTestData();
        Logger.log("Opening URL"+gettestdata.getUrl());
        driver.navigate().to(gettestdata.getUrl());
    }
}
