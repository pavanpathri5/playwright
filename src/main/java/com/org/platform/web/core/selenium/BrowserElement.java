package com.org.platform.web.core.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BrowserElement {
    WebDriver driver;
    protected WebElement element;
    private String identifier = "", value = "";
    public BrowserElement(String locatorName, String locatorValue, WebDriver driver)
    {
        this.driver=driver;
        identifier = locatorName;
        value = locatorValue;
    }

    private void getElement(WebDriver driver)
    {
        switch(identifier.trim().toLowerCase())
        {
            case "id": element = driver.findElement(By.id(value));
                break;
            case "xpath": element = driver.findElement(By.xpath(value));
                break;
            case "css" : element = driver.findElement(By.cssSelector(value));
                break;
            case "class": element = driver.findElement(By.className(value));
                break;
        }
    }
    public void openurl(String url){
        driver.navigate().to(url);
    }
    public void click(){
        getElement(driver);
        element.click();
    }

    public void scrollAndClick(){
        getElement(driver);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void write(String data){
        getElement(driver);
        element.sendKeys(data);
    }

    public void clickwithScript(){
        getElement(driver);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
