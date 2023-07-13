package com.org.tests;

import com.org.common.utils.GenericDataProvider;
import com.org.common.utils.JsonUtil;
import com.org.common.utils.Logger;
import com.org.helpers.APIHelper;
import com.org.platform.api.core.APIInstance;
import com.org.platform.api.core.APIRequest;
import com.org.platform.api.core.APIResponse;
import com.org.testdata.service.GetTestData;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Set;

public class APIExample extends APIHelper {
    GetTestData gettestdata=new GetTestData("api");
    JsonUtil jsonutil=new JsonUtil();
    APIInstance req=new APIInstance();
    @SneakyThrows
    @Test(priority = 1,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void testOne(JSONObject testData){
        String type="POST";
        String baseURL=gettestdata.getDemoServer()+"/Account/v1/User";
        String payload="{\n" +
                "  \"userName\": \"pavan2\",\n" +
                "  \"password\": \"Rgukt@123\"\n" +
                "}";
        HashMap<String, String> headers=null;
        HashMap<String, String> params=null;
        String query=null;
        String defaultRespons="{\n" +
                "  \"userID\": \"ba52fcb0-39dd-4420-8e49-d607d3ebaac4\",\n" +
                "  \"username\": \"pavan1\",\n" +
                "  \"books\": []\n" +
                "}";
        APIRequest req=new APIRequest(type,
                baseURL,
                payload,
                headers,
                params,
                query,
                defaultRespons);
        APIResponse response=instance.call(req);
        System.out.println(response.responseJSON);
    }
    @SneakyThrows
    @Test(priority = 2,dataProviderClass = GenericDataProvider.class,dataProvider = "GenericDataProvider",groups = {"sanity"})
    public void createUser(JSONObject testData){
        APIInstance instance=new APIInstance();
        APIRequest req=instance.makeRequest(testData);
        APIResponse res=instance.call(req);
        System.out.println(res.responseJSON);
        Assert.assertEquals("201",testData.getString("StatusCode"));
        Set<String> set= jsonutil.convertObjToString(testData.get("ignore keys"));
        Assert.assertTrue(jsonutil.compareJsonObjects(res.responseJSON,testData.get("response data").toString(),set));
        //Result result = CompareJson.compare(response.responseJSON, testdata.get("jsonPath"), "id", data, ignoreFieldsInJson, ignoreFieldsInInput, false);
    }

}
