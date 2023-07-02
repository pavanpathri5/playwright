package pages;

import com.microsoft.playwright.Page;
import com.org.platform.web.core.HtmlElement;

public class LoginPage {
    Page page;
    public HtmlElement allowLocation;

    public LoginPage(Page page) {
        this.page = page;
        allowLocation = new HtmlElement("xpath","//button[@aria-label='Allow Location']",page);
    }
    public void search(String text) {
        allowLocation.click();
    }
}
