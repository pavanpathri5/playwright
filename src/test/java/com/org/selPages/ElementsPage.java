package com.org.selPages;

import com.org.common.utils.Logger;
import com.org.platform.web.core.playwright.HtmlElement;
import com.org.platform.web.core.selenium.BrowserElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LoggingHandler;

public class ElementsPage {
    public WebDriver driver;
    public BrowserElement textBox,fullName,email,currentAddress,permanentAddress,submitBtn;
    public BrowserElement checkBox,checkboxDirectory,checkingbox;
    public BrowserElement radioButton;
    public ElementsPage(WebDriver driver){
        this.driver=driver;
        textBox=new BrowserElement("xpath","//span[contains(text(),'Text Box')]",driver);
        fullName = new BrowserElement("xpath","//input[@id='userName']",driver);
        email = new BrowserElement("xpath","//input[@id='userEmail']",driver);
        currentAddress = new BrowserElement("xpath","//textarea[@id='currentAddress']",driver);
        permanentAddress = new BrowserElement("xpath","//textarea[@id='permanentAddress']",driver);
        submitBtn = new BrowserElement("xpath","//button[@id='submit']",driver);

        checkBox=new BrowserElement("xpath","//span[contains(text(),'Check Box')]",driver);
        //checkingbox=new BrowserElement("xpath","//button[@title='Toggle']/following-sibling::label//span[@class='rct-checkbox']",driver);
        checkingbox=new BrowserElement("css",".rct-node-expanded > .rct-text .rct-checkbox > .rct-icon",driver);
        radioButton=new BrowserElement("xpath","//span[contains(text(),'Radio Button')]",driver);
    }
    public void clickOnOpenText(){
        Logger.log("click on Text Box section");
        textBox.scrollAndClick();
    }

    public void fillFormAndSubmit(String name,String eml,String caddr,String paddr){
        Logger.log("Fill the form and submit");
        fullName.write(name);
        email.write(eml);
        currentAddress.write(caddr);
        permanentAddress.write(paddr);
        submitBtn.scrollAndClick();
    }

    public void clickOnCheckBox(){
        Logger.log("Clicking on CHeckbox inside elements");
        checkBox.scrollAndClick();
    }

    public void clickCheckBoxDirectory(String data){
        Logger.log("Clicking on selected directory");
        //checkboxDirectory=new BrowserElement("css","//button[@title='Toggle']/following-sibling::label//span[contains(text(),'"+data+"')]",driver);
        checkboxDirectory=new BrowserElement("css",".rct-icon-expand-close",driver);
        checkboxDirectory.click();
    }

    public void clickCheckingbox(){
        Logger.log("clickn on check box");
        checkingbox.click();
    }

    public void clickOnRadioBtnPanel(){
        Logger.log("Clicking on RadionButton Panel");
        radioButton.click();
    }
}
