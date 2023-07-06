package com.org.platform.web.core.selenium;

import com.org.common.config.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browsers {
    WebDriver driver;

    public WebDriver setDriver(){
        switch (new Environment().readProperties("browserType")){
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver=new ChromeDriver(options);
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }

    public void tearDown(){
        driver.close();
    }
}
