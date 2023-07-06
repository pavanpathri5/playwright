package testdata.service;

import com.org.common.config.Environment;
import com.org.common.io.YMLParser;
import com.org.common.utils.FileUtils;
import lombok.SneakyThrows;
import testdata.models.TestData;

public class GetTestData {
    FileUtils fileUtils=new FileUtils();
    String fileName;
    TestData td=null;
    YMLParser yml;

    public GetTestData(){
        String environment=new Environment().readProperties("env");
        this.yml=new YMLParser(td,TestData.class,"application-"+environment+".yml");
    }

@SneakyThrows
public String getUrl(){
        return yml.getValue("getUrl").toString();
}

}
