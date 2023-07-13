package com.org.selPages;

import com.org.common.utils.Logger;
import com.org.helpers.DevelopTools;
import com.org.platform.web.core.selenium.BrowserElement;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ElementsPage {
    public WebDriver driver;
    public BrowserElement textBox,fullName,email,currentAddress,permanentAddress,submitBtn;
    public BrowserElement checkBox,checkboxDirectory,checkingbox;
    public BrowserElement radioButton,radioBtnYes,radioBtnImp,radioBtnNo;
    public BrowserElement webTableBtn,tableAddBtn,webtFirstName,webtLastName,webtEmail,webtAge,webtSalary,webtDept,webtSubmit,webtSearch;
    public BrowserElement tablerHeader,tableCols,tableRows,currentRow,currentColumn,tablerecord;
    public BrowserElement buttonPage,doubleClick,rightClick,clickMe;
    public BrowserElement linksPage,homeLink,homeklink,createdLink,noContentLink,movedLink,badRequestlink,unauthorizedLink,forbiddentLink,notfoundLink;
    public ElementsPage(WebDriver driver){
        this.driver=driver;
        textBox=new BrowserElement("xpath","//span[contains(text(),'Text Box')]",driver);
        fullName = new BrowserElement("xpath","//input[@id='userName']",driver);
        email = new BrowserElement("xpath","//input[@id='userEmail']",driver);
        currentAddress = new BrowserElement("xpath","//textarea[@id='currentAddress']",driver);
        permanentAddress = new BrowserElement("xpath","//textarea[@id='permanentAddress']",driver);
        submitBtn = new BrowserElement("xpath","//button[@id='submit']",driver);

        checkBox=new BrowserElement("xpath","//span[contains(text(),'Check Box')]",driver);
        //checkingbox=new BrowserElement("xpath","//button[@title='Toggle']/following-sibling::label//span[@class='rct-checkbox']",driver);
        checkingbox=new BrowserElement("css",".rct-node-expanded > .rct-text .rct-checkbox > .rct-icon",driver);
        radioButton=new BrowserElement("xpath","//span[contains(text(),'Radio Button')]",driver);
        radioBtnYes=new BrowserElement("xpath","//div/input[@id='yesRadio']",driver);
        radioBtnImp=new BrowserElement("xpath","//div/input[@id='impressiveRadio']",driver);
        radioBtnNo=new BrowserElement("xpath","//div/input[@id='noRadio']",driver);

        webTableBtn=new BrowserElement("xpath","//span[contains(text(),'Web Tables')]",driver);
        tableAddBtn=new BrowserElement("xpath","//button[@id='addNewRecordButton']",driver);
        webtFirstName=new BrowserElement("xpath","//input[@placeholder='First Name']",driver);
        webtLastName=new BrowserElement("xpath","//input[@placeholder='Last Name']",driver);
        webtEmail=new BrowserElement("xpath","//input[@placeholder='name@example.com']",driver);
        webtAge=new BrowserElement("xpath","//input[@placeholder='Age']",driver);
        webtSalary=new BrowserElement("xpath","//input[@placeholder='Salary']",driver);
        webtDept=new BrowserElement("xpath","//input[@placeholder='Department']",driver);
        webtSubmit=new BrowserElement("xpath","//button[@id='submit']",driver);
        webtSearch=new BrowserElement("xpath","//input[@placeholder='Type to search']",driver);

        tablerHeader=new BrowserElement("xpath","(//div[@class='rt-table']//div[@role='row'])[1]",driver);
        tableCols=new BrowserElement("xpath","(//div[@class='rt-table']//div[@role='row'])[1]//div//div[@class='rt-resizable-header-content']",driver);
        tableRows=new BrowserElement("xpath","//div[@class='rt-table']//div[@role='row']",driver);

        buttonPage=new BrowserElement("xpath","//span[contains(text(),'Buttons')]",driver);
        doubleClick=new BrowserElement("xpath","//button[@id='doubleClickBtn']",driver);
        rightClick=new BrowserElement("xpath","//button[@id='rightClickBtn']",driver);
        clickMe=new BrowserElement("xpath","//button[text()='Click Me']",driver);

        linksPage=new BrowserElement("xpath","//span[contains(text(),'Links')]",driver);
        homeLink=new BrowserElement("xpath","//a[@id='simpleLink']",driver);
        homeklink=new BrowserElement("xpath","//a[@id='dynamicLink']",driver);
        createdLink=new BrowserElement("xpath","//a[@id='created']",driver);
        noContentLink=new BrowserElement("xpath","//a[@id='no-content']",driver);
        movedLink=new BrowserElement("xpath","//a[@id='moved']",driver);
        badRequestlink=new BrowserElement("xpath","//a[@id='bad-request']",driver);
        unauthorizedLink=new BrowserElement("xpath","//a[@id='unauthorized']",driver);
        forbiddentLink=new BrowserElement("xpath","//a[@id='forbidden']",driver);
        notfoundLink=new BrowserElement("xpath","//a[@id='invalid-url']",driver);

    }
    public void clickOnOpenText(){
        Logger.log("click on Text Box section");
        textBox.scrollAndClick();
    }

    public void fillFormAndSubmit(String name,String eml,String caddr,String paddr){
        Logger.log("Fill the form and submit");
        fullName.write(name);
        email.write(eml);
        currentAddress.write(caddr);
        permanentAddress.write(paddr);
        submitBtn.scrollAndClick();
    }

    public void clickOnCheckBox(){
        Logger.log("Clicking on CHeckbox inside elements");
        checkBox.scrollAndClick();
    }

    public void clickCheckBoxDirectory(String data){
        Logger.log("Clicking on selected directory");
        //checkboxDirectory=new BrowserElement("css","//button[@title='Toggle']/following-sibling::label//span[contains(text(),'"+data+"')]",driver);
        checkboxDirectory=new BrowserElement("css",".rct-icon-expand-close",driver);
        checkboxDirectory.click();
    }

    public void clickCheckingbox(){
        Logger.log("clickn on check box");
        checkingbox.click();
    }

    public void clickOnRadioBtnPanel(){
        Logger.log("Clicking on RadionButton Panel");
        radioButton.click();
    }
    public void clickonRadioButtonYes(){
        Logger.log("Clicking on radio button Yes");
        radioBtnYes.clickwithScript();
    }

    public Boolean radioBtnIsselected(){
        Logger.log("Radio button selected");
        return radioBtnImp.isSelected();
    }

    public Boolean radioButtonDisplayed(){
        Logger.log("is radio button selected or not");
        return radioBtnImp.isVisible();
    }

    public Boolean radioButtonIsenabled(){
        Logger.log("is radio button enabled");
        return radioBtnNo.isEnabled();
    }

    public void clickWebTablePanel(){
        Logger.log("Clicking on Web table panel");
        webTableBtn.click();
    }

    public List<HashMap<String,String>> getTableData(){
        List<HashMap<String,String>> list=new ArrayList<>();
        BrowserElement []rows = tableRows.getSuBrowserElement("xpath", "//div[@class='rt-table']//div[@class='rt-tbody']/div[@class='rt-tr-group']");
        System.out.println(rows.length);
        for(int i=1;i<rows.length;i++){
            BrowserElement cols[] = tableCols.getSuBrowserElement("xpath","(//div[@class='rt-table']//div[@class='rt-tbody']/div[@class='rt-tr-group'])["+i+"]//div[@class='rt-td']");
            HashMap<String,String> hm=new HashMap<>();
            for(int j=0;j<cols.length;j++){
                tablerecord=new BrowserElement("xpath","((//div[@class='rt-table']//div[@class='rt-tbody']/div[@class='rt-tr-group'])["+i+"]//div[@class='rt-td'])["+j+"]",driver);
                hm.put(""+j,cols[j].read());System.out.println(cols[j].read());
            }
            list.add(hm);
        }
        return list;
    }

    public void addRow(String fn,String ln,String email,String age,String sal,String dept){
        tableAddBtn.click();
        webtFirstName.write(fn);
        webtLastName.write(ln);
        webtEmail.write(email);
        webtAge.write(age);
        webtSalary.write(sal);
        webtDept.write(dept);
        webtSubmit.click();
    }

    public void clickButtonPanel(){
        buttonPage.click();
    }

    public void clickButtons(){
        doubleClick.doubleclick();
        rightClick.rightClick();
        clickMe.click();
    }

    public void openLinksPage(){
        linksPage.click();
    }

    public Boolean verifyHomeLink(){
        homeLink.click();
        homeLink.openNewTab();
        HomePage hp=new HomePage(driver);
        return hp.verifyElementsSection();
    }

    public void closeHomeTab(){
        homeLink.closeNewTab();
    }

    public void verifyCreatedNetworkCall(){
        DevelopTools developTools=new DevelopTools(driver);
        developTools.startsession();
        createdLink.click();
        developTools.verifyNetworkreqHeaders();
    }

}
