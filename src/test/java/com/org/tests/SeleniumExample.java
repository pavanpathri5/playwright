package com.org.tests;

import com.org.common.utils.GenericDataProvider;
import com.org.helpers.SelTestHelper;
import com.org.selPages.ElementsPage;
import com.org.selPages.HomePage;
import com.org.selPages.StartTest;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class SeleniumExample extends SelTestHelper {
    @SneakyThrows
    @Test(priority = 1,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testOne(JSONObject testData){
        StartTest start=new StartTest(driver);
        HomePage hp=new HomePage(driver);
        ElementsPage elements=new ElementsPage(driver);
        start.openUrl();
        hp.openElementsSection();
        elements.clickOnOpenText();
        elements.fillFormAndSubmit(testData.get("name").toString(),testData.get("email").toString(),testData.get("currentaddress").toString(),testData.get("permanentaddress").toString());
    }

    @SneakyThrows
    @Test(priority = 2,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testCheckBox(JSONObject testData){
        StartTest start=new StartTest(driver);
        HomePage hp=new HomePage(driver);
        ElementsPage elements=new ElementsPage(driver);
        start.openUrl();
        hp.openElementsSection();
        elements.clickOnCheckBox();
        elements.clickCheckBoxDirectory("Home");
        elements.clickCheckingbox();
    }

    @SneakyThrows
    @Test(priority = 3,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testRadioButton(JSONObject testData){
        StartTest start=new StartTest(driver);
        HomePage hp=new HomePage(driver);
        ElementsPage elements=new ElementsPage(driver);
        start.openUrl();
        hp.openElementsSection();
        elements.clickOnCheckBox();
    }

}
