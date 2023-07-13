package com.org.helpers;

import com.org.platform.web.core.selenium.Browsers;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v114.network.Network;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

public class SelTestHelper {
    private static final Logger logger = Logger.getLogger(SelTestHelper.class.getName());
    Browsers brw=new Browsers();
    public SoftAssert softAssert;
    public WebDriver driver;
   @BeforeMethod(alwaysRun = true)
    public WebDriver setup(){
        Allure.step("Start test");
        softAssert = new SoftAssert();
        return this.driver=brw.setDriver();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        /*if(result.getStatus()==2)
            Allure.addAttachment(result.getTestName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
*/        Allure.step("exit test");
        brw.tearDown();
    }
}
