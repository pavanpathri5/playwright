package com.org.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.org.common.config.Environment;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Environment env=new Environment();
    public static Object[][] getDataObject(ITestContext ctx, String testMethodName, String filePath) {
        JSONArray toCollection;
        ArrayList<JsonObject> shortListedTCs = new ArrayList<>();
        Object[][] obj=null;
        try {
            File file=new File(filePath);
            System.out.println(filePath);
            String content= FileUtils.readFileToString(file,"utf-8");
            JSONObject json=new JSONObject(content);
            toCollection=json.getJSONArray(testMethodName);
            obj=new Object[toCollection.length()][1];
            for(int i=0;i<obj.length;i++){
                obj[i][0]=(JSONObject)toCollection.get(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
