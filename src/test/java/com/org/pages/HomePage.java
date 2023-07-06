package com.org.pages;

import com.microsoft.playwright.Page;
import com.org.platform.web.core.playwright.HtmlElement;

public class HomePage {
    Page page;
    public HtmlElement Elements;
    public HomePage(Page page) {
        this.page = page;
        Elements = new HtmlElement("xpath","//div[@class='card-body']//h5[contains(text(),'Elements')]",page);
    }
    public void elementsClick() {
        Elements.click();
    }
}
