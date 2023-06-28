package tests;

import com.google.gson.JsonObject;
import com.org.common.utils.GenericDataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.StartTest;

public class ExampleOne extends TestHelper {

    @Test(priority = 0,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity","regression"})
    public void testOne(JsonObject testData){
        StartTest start=new StartTest(page);
        start.openUrl("ajio.com");
        LoginPage loginpage=new LoginPage(page);
        loginpage.allowLocation.click();
    }

    @Test(priority = 1,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testTwo(){
        StartTest start=new StartTest(page);
        start.openUrl("ajio.com");
        LoginPage loginpage=new LoginPage(page);
        loginpage.allowLocation.click();
    }
}
