package com.org.platform.api.core;

import java.util.HashMap;

public class APIRequest {
    public String baseURL, payload, query, defaultResponse, type;
    public HashMap<String, String> headers, params;

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setParams(String key, String value) {
        params.put(key, value);
    }

    public void setHeaders(String key, String value) {
        headers.put(key, value);
    }

    public APIRequest(String type, String baseURL, String payload, HashMap<String, String> headers, HashMap<String, String> params, String query, String defaultResponse)
    {
        this.type = type;
        this.baseURL = baseURL;
        this.payload = payload;
        this.query = query;
        this.defaultResponse = defaultResponse;
        this.headers = headers;
        this.params = params;
    }
}
