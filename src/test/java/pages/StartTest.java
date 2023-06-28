package pages;

import com.microsoft.playwright.Page;
import com.org.platform.web.core.WebsiteDataManager;

public class StartTest {
    Page page;
    public StartTest(Page page){
        this.page=page;
    }

    public void openUrl(String portal){
        WebsiteDataManager web=new WebsiteDataManager(portal);
        page.navigate(web.getURL());
    }
}
