package com.org.tests;

import com.org.common.utils.GenericDataProvider;
import com.org.helpers.TestHelper;
import com.org.pages.ElementsPage;
import com.org.pages.HomePage;
import org.json.JSONObject;
import org.testng.annotations.Test;
import com.org.pages.StartTest;

public class PlayWrightExample extends TestHelper {
    @Test(priority = 0,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testTwo(JSONObject testData){
        StartTest start=new StartTest(page);
        HomePage home=new HomePage(page);
        ElementsPage elements=new ElementsPage(page);
        start.openUrl();
        home.elementsClick();
        elements.textBoxClick();
        elements.fillFormAndSubmit(testData.get("name").toString(),testData.get("email").toString(),testData.get("currentaddress").toString(),testData.get("permanentaddress").toString());
    }
}
