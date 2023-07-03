package com.org.platform.api.core;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class APIInstance {

    /*public APIRequest makeRequest(APIRequestType type) throws IOException, ParseException
    {
        APIRequest req = xml.readAPIFromconfig(server, uri, type.name().toLowerCase());

        if(req == null)
        {
            Logger.log("API req coming null: server: "+server + " :: uri: " + uri);
            throw new ParseException(0);
        }

        req.baseURL = String.format(req.baseURL, uri);
        return req;
    }*/
    public APIResponse call(APIRequest request){
        String baseURL = request.baseURL;
        HashMap<String, String> headers = request.headers;
        HashMap<String, String> params = request.params;
        String payload = request.payload;
        // Set the media type as JSON
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        // Add parameter to the base URL
        baseURL = baseURL + transformParameters(params);

        // Add the URL
        Request.Builder build = new Request.Builder().url(baseURL);

        APIRequestType typeReq = APIRequestType.valueOf(request.type.trim().toUpperCase());

        switch(typeReq)
        {
            case POST:
                RequestBody body_post = RequestBody.create(mediaType, payload);
                build = build.post(body_post); break;

            case GET: build = build.get(); break;

            case PUT:
                RequestBody body_put=RequestBody.create(mediaType, payload);
                build = build.put(body_put);break;

            case DELETE:
                RequestBody body_delete=RequestBody.create(mediaType, payload);
                build = build.delete(body_delete);break;

            default:
                break;
        }
        // Add headers
        if(headers!=null) {
            for (Map.Entry<String, String> entry : headers.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                build = build.addHeader(key, value);
            }
        }

        Request req = build.build();
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        Response response = null;
        try {
            response = client.newCall(req).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Populate the response
        APIResponse apiResponse = null;
        try {
            apiResponse = new APIResponse(response.code(), response.body().string().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apiResponse;
    }

    private String transformParameters(HashMap<String, String> params)
    {
        //	System.out.println(" >>> " + params.size());
        String parameterStr = "";
        if((params != null) && (params.isEmpty() == false))
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                parameterStr = parameterStr + "&" + key + "=" + value;
            }
            parameterStr = "?" + parameterStr.trim().substring(1);
        }
        return parameterStr;
    }
}
