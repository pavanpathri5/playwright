package com.org.platform.web.core.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class BrowserElement {
    WebDriver driver;
    protected WebElement element;
    private String identifier = "", value = "";
    Actions actions;
    List<String> browserTabs;
    public BrowserElement(String locatorName, String locatorValue, WebDriver driver)
    {
        this.driver=driver;
        identifier = locatorName;
        value = locatorValue;
        actions = new Actions(driver);
    }

    private BrowserElement(WebElement el)  // explain this CH  =2
    {
        this.element = el;
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

    public BrowserElement[] getSuBrowserElement(String key, String value) {
        getElement(driver);
        // TODO: menu based key
        List<WebElement> list = element.findElements(By.xpath(value));
        BrowserElement []els = new BrowserElement[list.size()];
        for(int i = 0; i < list.size(); i++)
        {
            els[i] = new BrowserElement(list.get(i));
        }
        return els;
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

    public Boolean isSelected(){
        getElement(driver);
        return element.isSelected();
    }
    public Boolean isVisible(){
        getElement(driver);
        return element.isDisplayed();
    }

    public Boolean isEnabled(){
        getElement(driver);
        return element.isEnabled();
    }

    public String read(){
        getElement(driver);
        return element.getText();
    }

    public void doubleclick(){
        getElement(driver);
        actions.doubleClick(element).perform();
    }

    public void rightClick(){
        getElement(driver);
        actions.contextClick(element).perform();
    }

    public void openNewTab(){
        browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs .get(1));
    }

    public void closeNewTab(){
        driver.close();
        driver.switchTo().window(browserTabs .get(0));
    }
}
