package com.org.platform.web.core.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenSize;

import java.awt.*;

public class Browsers {
    Playwright playwright;
    Browser browser;
    // New instance for each test method.
    BrowserContext context;
    Page page;
    public Page setBrowser(){
        playwright = Playwright.create();
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        int width= (int)screensize.getWidth();
        int height= (int) screensize.getHeight();
        System.out.println("width: "+width+"Height: "+height);
        switch("CHROME"){
            case "CHROME":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width,height));
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
