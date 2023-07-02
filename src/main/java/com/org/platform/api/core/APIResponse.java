package com.org.platform.api.core;

public class APIResponse {
    public int statusCode;
    public String responseJSON;

    public APIResponse(int statusCode, String responseJSON)
    {
        this.statusCode = statusCode;
        this.responseJSON = responseJSON;
    }
}
