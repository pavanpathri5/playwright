package com.org.pages;

import com.microsoft.playwright.Page;
import com.org.platform.web.core.playwright.HtmlElement;

public class ElementsPage {
    Page page;
    public HtmlElement textBox,fullName,email,currentAddress,permanentAddress,submitBtn;
    private String caddr;

    public ElementsPage(Page page) {
        this.page = page;
        textBox = new HtmlElement("xpath","//span[contains(text(),'Text Box')]",page);
        fullName = new HtmlElement("xpath","//input[@id='userName']",page);
        email = new HtmlElement("xpath","//input[@id='userEmail']",page);
        currentAddress = new HtmlElement("xpath","//textarea[@id='currentAddress']",page);
        permanentAddress = new HtmlElement("xpath","//textarea[@id='permanentAddress']",page);
        submitBtn = new HtmlElement("xpath","//button[@id='submit']",page);
    }
    public void textBoxClick() {
        textBox.click();
    }
    public void fillFormAndSubmit(String name,String eml,String caddr,String paddr){
        fullName.fill(name);
        email.fill(eml);
        currentAddress.fill(caddr);
        permanentAddress.fill(paddr);
        submitBtn.click();
    }
}
