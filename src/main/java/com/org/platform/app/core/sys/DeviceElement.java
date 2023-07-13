package com.org.platform.app.core.sys;

import com.org.platform.app.core.devices.DeviceInteract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class DeviceElement
{
    private WebElement element;
    private String identifier = "", value = "";
    private DeviceInteract device;
    private boolean isLive = false;

    private void getElement()
    {
        if(isLive)
            return;
        device.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        switch(identifier.trim().toLowerCase())
        {
            case "id": element = device.driver.findElement(By.id(value));
                break;
            case "xpath": element = device.driver.findElement(By.xpath(value));
                break;
            case "class": element = device.driver.findElement(By.className(value));
                break;
        }
    }
    public void tap()
    {
        getElement();
        element.click();
    }

    public String read()
    {
        getElement();
        return element.getText();
    }

    public void write(String data)
    {
        getElement();
        element.clear();
        element.sendKeys(data);
    }

    public void writeKeys(String data) {
        getElement();
        element.sendKeys(data);
    }

    public DeviceElement(DeviceInteract device, String string, String string2)
    {
        this.device = device;
        identifier = string;
        value = string2;
    }

    public boolean isVisible()
    {
        try
        {
            getElement();
            boolean flag = element.isDisplayed();
            return flag;
        }
        catch(Exception e)
        {
            return false;
        }

    }

    public DeviceElement(DeviceInteract device, WebElement el)
    {
        this.device = device;
        this.element = el;
        isLive = true;
    }

    public boolean isEnabled()
    {
        getElement();
        return element.isEnabled();
    }

    public boolean isSelected()
    {
        getElement();
        return element.isSelected();
    }

    public String getAttributes(String attribute)
    {
        getElement();
        return element.getAttribute(attribute);
    }


}
