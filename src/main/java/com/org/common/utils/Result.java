package com.org.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private boolean status = true;
    private String path;
    private String identifier;
    private boolean isJsonArray;
    private List<Map<String, Object>> missingFieldsInJson = new ArrayList<>();
    private List<Map<String, Object>> missingFieldsInInput = new ArrayList<>();
    private List<Map<String, Object>> differences = new ArrayList<>();
    private List<Map<String, Object>> missingRecordsinJson = new ArrayList<>();
    private List<Map<String, Object>> missingRecordsinInput = new ArrayList<>();

    public Result(String path, String identifier, boolean isJsonArray) {
        this.path = path;
        this.identifier = identifier;
        this.isJsonArray = isJsonArray;
    }

    public String getErrorMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("status="+status+"\n");
        builder.append("path="+path+"\n");
        builder.append("identifier="+identifier+"\n");
        builder.append("isJsonArray="+isJsonArray+"\n");
        builder.append("missingFieldsInJson="+missingFieldsInJson+"\n");
        builder.append("missingFieldsInInput="+missingFieldsInInput+"\n");
        builder.append("missingRecordsinJson="+missingRecordsinJson+"\n");
        builder.append("missingRecordsinInput="+missingRecordsinInput+"\n");
        builder.append("Changes="+differences);
        Logger.log("****** DATA COMPARISION info: *******");
        Logger.log(builder.toString());
        Logger.log("****** DATA COMPARISION END *******");
        return builder.toString();
    }

    public boolean getStatus() {
        return status;
    }


    public void addDifference(Map<String, Object> keyMap, String key, Object jsonValue, Object inputValue) {
        status = false;
        HashMap<String, Object> map = new HashMap<>(keyMap);
        map.put("jsonValue", jsonValue);
        map.put("inputValue", inputValue);
        map.put("key", key);
        differences.add(map);
    }

    public void addMissingRecordsInJson(Map<String, Object> keyMap) {
        status = false;
        missingRecordsinJson.add(keyMap);
    }

    public void addMissingRecordsInInput(Map<String, Object> keyMap) {
        status = false;
        missingRecordsinInput.add(keyMap);
    }

    public void addMissingFieldsInJson(Map<String, Object> keyMap, String missingField) {
        status = false;
        HashMap<String, Object> map = new HashMap<>(keyMap);
        map.put("missingField", missingField);
        missingRecordsinJson.add(map);
    }

    public void addMissingFieldsInInput(Map<String, Object> keyMap, String missingField) {
        status = false;
        HashMap<String, Object> map = new HashMap<>(keyMap);
        map.put("missingField", missingField);
        missingRecordsinInput.add(map);
    }
}
