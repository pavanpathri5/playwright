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
        elements.clickOnRadioBtnPanel();
        elements.clickonRadioButtonYes();
        elements.radioButtonDisplayed();
        elements.radioButtonIsenabled();
    }

    @SneakyThrows
    @Test(priority = 4,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testWebTables(JSONObject testData){
        StartTest start=new StartTest(driver);
        HomePage hp=new HomePage(driver);
        ElementsPage elements=new ElementsPage(driver);
        start.openUrl();
        hp.openElementsSection();
        elements.clickWebTablePanel();
        elements.getTableData();
        elements.addRow(testData.get("fn").toString(),testData.get("ln").toString(),testData.get("email").toString(),testData.get("age").toString(),testData.get("sal").toString(),testData.get("dept").toString());
    }

    @SneakyThrows
    @Test(priority = 5,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testButtons(JSONObject testData) {
        StartTest start = new StartTest(driver);
        HomePage hp = new HomePage(driver);
        ElementsPage elements = new ElementsPage(driver);
        start.openUrl();
        hp.openElementsSection();
        elements.clickButtonPanel();
        elements.clickButtons();
    }

    @SneakyThrows
    @Test(priority = 6,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testLinks(JSONObject testData) {
        StartTest start = new StartTest(driver);
        HomePage hp = new HomePage(driver);
        ElementsPage elements = new ElementsPage(driver);
        start.openUrl();
        hp.openElementsSection();
        elements.openLinksPage();
        softAssert.assertTrue(elements.verifyHomeLink());
        elements.closeHomeTab();
        softAssert.assertAll();
        elements.verifyCreatedNetworkCall();

    }


}
