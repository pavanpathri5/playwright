package com.org.platform.api.core;

import com.org.common.config.Environment;
import com.org.common.utils.JsonUtil;
import com.org.common.utils.RandomData;
import org.json.JSONObject;

import java.util.HashMap;

public class JsonLib {
    public APIRequest readAPIData(JSONObject testData){
        RandomData rand = new RandomData();
        Environment environment = new Environment();
        JsonUtil jsonutil=new JsonUtil();
        String requestType=testData.getString("request type");
        String __URL=testData.getString("url");
        String __payload=testData.get("body").toString();
        HashMap<String,String> headerMap=jsonutil.getmapFromJson(testData.get("headers").toString());
        HashMap<String,String> paramMap=null;
        String __query=null;
        String __response=null;
        return new APIRequest(requestType.trim().toLowerCase(), __URL, __payload, headerMap, paramMap, __query, __response);
    }
}
