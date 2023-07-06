package com.org.common.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.org.common.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class YMLParser {
    Object obj;
    Class C;
    String fileName;

    public YMLParser(Object obj,Class C,String fileName){
        this.obj=obj;
        this.C=C;
        this.fileName = fileName;
    }
    public Object getDataObject() throws IOException {
        FileUtils fileUtils=new FileUtils();
        File file = new File(fileUtils.getFilePath(fileName));
// Instantiating a new ObjectMapper as a YAMLFactory
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        obj = om.readValue(file, C);
// Printing out the information
        return obj;
    }
    public Object getValue(String methodName) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object d= getDataObject();
        Method m=d.getClass().getDeclaredMethod(methodName);
        return m.invoke(obj);
    }
}
