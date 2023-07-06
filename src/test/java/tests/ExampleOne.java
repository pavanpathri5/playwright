package tests;

import com.google.gson.JsonObject;
import com.org.common.utils.GenericDataProvider;
import org.json.JSONObject;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.StartTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ExampleOne extends TestHelper {
    @Test(priority = 0,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity","regression"})
    public void testOne(Object testData){
        StartTest start=new StartTest(page);
        start.openUrl();
    }

    @Test(priority = 1,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testTwo(Object testData){
        StartTest start=new StartTest(page);
        start.openUrl();
    }
}
