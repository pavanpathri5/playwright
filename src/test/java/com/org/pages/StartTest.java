package com.org.pages;

import com.microsoft.playwright.Page;
import com.org.testdata.service.GetTestData;

public class StartTest {
    Page page;
    public StartTest(Page page){
        this.page=page;
    }

    public void openUrl(){
        GetTestData gettestdata=new GetTestData();
        page.navigate(gettestdata.getUrl());
    }
}
