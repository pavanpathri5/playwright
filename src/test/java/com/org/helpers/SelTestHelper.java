package com.org.helpers;

import com.org.platform.web.core.selenium.Browsers;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.logging.Logger;

public class SelTestHelper {
    private static final Logger logger = Logger.getLogger(SelTestHelper.class.getName());
    Browsers brw=new Browsers();
    public WebDriver driver;
    @BeforeTest
    public WebDriver setup(){
        Allure.step("Start test");
        return this.driver=brw.setDriver();
    }

    @AfterTest
    public void tearDown(){
        Allure.step("exit test");
        brw.tearDown();
    }
}
