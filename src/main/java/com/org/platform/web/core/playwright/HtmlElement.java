package com.org.platform.web.core.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.concurrent.TimeUnit;

public class HtmlElement {
    Locator locator;
    Page page;
    private String identifier = "", value = "";
    public HtmlElement(String locatorName, String locatorValue,Page page)
    {
        this.page=page;
        identifier = locatorName;
        value = locatorValue;
    }

    private void getElement(Page page)
    {
        switch(identifier.trim().toLowerCase())
        {
            case "label": locator = page.getByLabel(value);
                break;
            case "xpath": locator = page.locator(value);
                break;
        }
    }
    public void openurl(String url){
        page.navigate(url);
    }
    public void click(){
        getElement(page);
        locator.click();
    }
    public void fill(String data){
        getElement(page);
        locator.fill(data);
    }
}
