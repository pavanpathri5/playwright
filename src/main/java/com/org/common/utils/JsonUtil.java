package com.org.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.org.common.config.Environment;
import org.testng.ITestContext;

import java.util.ArrayList;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Environment env=new Environment();
    public static Object[][] getDataObject(ITestContext ctx, String testMethodName, String filePath) {
        JsonArray toCollection;
        ArrayList<JsonObject> shortListedTCs = new ArrayList<>();
        JsonObject[][] tcList = null;
        Integer threadCount;
        String tdFileName;

        /*try {
            String[] paramValues = ctx.getCurrentXmlTest().getParameter(testMethodName.trim()).split(",");

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return tcList;
    }
}
