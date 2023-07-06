package tests;

import com.microsoft.playwright.Page;
import com.org.platform.web.core.Browsers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestHelper {
    Browsers brw=new Browsers();
    Page page;
    @BeforeTest
    public Page setup(){
        return page=brw.setBrowser();
    }

    @AfterTest
    public void tearDown(){
        brw.tearDownBrowser();
    }


}
