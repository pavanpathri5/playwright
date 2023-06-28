package com.org.platform.web.core;

import com.microsoft.playwright.*;

public class Browsers {
    Playwright playwright;
    Browser browser;
    // New instance for each test method.
    BrowserContext context;
    Page page;
    public Page setBrowser(){
        playwright = Playwright.create();
        switch("CHROME"){
            case "CHROME":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
                context = browser.newContext();
                page = context.newPage();
                break;
            case "FIREFOX":
                browser = playwright.firefox().launch();
                context = browser.newContext();
                page = context.newPage();
                break;
            default :
                System.out.println("No browser selected");
                return null;
        }
        return page;
    }
    public void tearDownBrowser(){
        context.close();
    }
}
