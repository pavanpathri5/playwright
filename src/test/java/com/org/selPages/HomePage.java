package com.org.selPages;

import com.org.platform.web.core.selenium.BrowserElement;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public BrowserElement elements;
    public HomePage(WebDriver driver){
        elements=new BrowserElement("xpath","//div[@class='card-body']//h5[contains(text(),'Elements')]",driver);
    }
    public void openElementsSection(){
        elements.scrollAndClick();
    }

    public Boolean verifyElementsSection(){
       return elements.isVisible();
    }

}
