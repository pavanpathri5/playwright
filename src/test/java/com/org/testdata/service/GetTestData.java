package com.org.testdata.service;

import com.org.common.config.Environment;
import com.org.common.io.YMLParser;
import com.org.common.utils.FileUtils;
import com.org.testdata.models.APIData;
import com.org.testdata.models.Demoqa;
import com.org.testdata.models.TestData;
import lombok.SneakyThrows;

public class GetTestData {
    FileUtils fileUtils=new FileUtils();
    String fileName;
    TestData td=null;
    YMLParser yml;

    public GetTestData(){
        String environment=new Environment().readProperties("env");
        this.yml=new YMLParser(td,TestData.class,"application-"+environment+".yml");
    }

    public GetTestData(String api){
        String environment=new Environment().readProperties("env");
        this.yml=new YMLParser(td, APIData.class,"application-api-"+environment+".yml");
    }

@SneakyThrows
public String getUrl(){
        return yml.getValue("getUrl").toString();
}


@SneakyThrows
public String getDemoServer(){
    Demoqa demo= (Demoqa) yml.getValue("getDemoqa");
    return demo.getUrl();
    }
}
