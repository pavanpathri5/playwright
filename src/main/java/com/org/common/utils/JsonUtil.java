package com.org.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.org.common.config.Environment;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Environment env=new Environment();
    public static final String equalityString = "[?(@.%s==%s)]";
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

    public HashMap<String,String> getmapFromJson(String jsonString){
        // Convert the JSON object to a JsonNode
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // Convert the JsonNode to a HashMap
        HashMap<String, String> hashMap = objectMapper.convertValue(jsonNode, HashMap.class);
        // Print the HashMap
        return hashMap;
    }

    public boolean compareJsonObjects(String json1, String json2, Set<String> ignoredKeys) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tree1 = objectMapper.readTree(json1);
        JsonNode tree2 = objectMapper.readTree(json2);
        return compareJsonNodes(tree1, tree2, ignoredKeys);
    }

    public boolean compareJsonNodes(JsonNode node1, JsonNode node2, Set<String> ignoredKeys) {
        if (!node1.isObject() || !node2.isObject()) {
            return node1.equals(node2);
        }
        Iterator<String> fieldNames = node1.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if (ignoredKeys.contains(fieldName)) {
                continue;
            }
            JsonNode child1 = node1.get(fieldName);
            JsonNode child2 = node2.get(fieldName);

            if (!compareJsonNodes(child1, child2, ignoredKeys)) {
                return false;
            }
        }
        return true;
    }

    @SneakyThrows
    public Set<String> convertObjToString(Object json){
        String jsonstring=json.toString();
        // Convert the JSON object to a Set
        Set<String> set = objectMapper.readValue(jsonstring, new TypeReference<Set<String>>() {});
        return set;
    }

    public static Result compare(String jsonString, String path, String identifier, List<Map<String, Object>> expectedData,
                                 Set<String> ignoreFieldsInJson, Set<String> ignoreFieldsInInput, boolean isJsonArray) {
        Result result = new Result(path, identifier, isJsonArray);

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);

        Logger.log("convert to document : "+document);
        System.out.println("check ing the fdflsdjfsd:"+document);

        HashSet<Map<String, Object>> expectedKeys = new HashSet<>();
        for (Map<String, Object> expectedMap : expectedData) {
            Object id = expectedMap.get(identifier);

            HashMap<String, Object> keyMap = new HashMap<>();
            keyMap.put(identifier, id + "");
            expectedKeys.add(keyMap);
            List<Map<String, Object>> jsonDataList = new ArrayList<>();
            if (isJsonArray) {
                String recordPath = path + String.format(equalityString, identifier, id);
                jsonDataList = JsonPath.read(document, recordPath);
            } else {

                Object record = JsonPath.read(document, path);
                if (record instanceof Map)
                    jsonDataList.add((Map<String, Object>) record);
                else
                    jsonDataList = (List<Map<String, Object>>) record;
            }


            if (jsonDataList.size() != 0) {
                for (Map<String, Object> actualMap : jsonDataList) {

                    for (String key : actualMap.keySet()) {

                        if (ignoreFieldsInJson != null && ignoreFieldsInJson.contains(key))
                            continue;

                        Object actualValue = actualMap.get(key);
                        if (expectedMap.containsKey(key)) {
                            Object expectedValue = expectedMap.get(key);

                            if (expectedValue == null && actualValue == null)
                                continue;

                            if (expectedValue == null || actualValue == null || !(expectedValue.toString().equals(actualMap.get(key).toString()))) {
                                result.addDifference(keyMap, key, actualValue, expectedValue);
                            }
                        } else {
                            if (!(actualValue != null && (actualValue instanceof JSONArray || actualValue instanceof JSONObject || actualValue instanceof Map))) {
                                result.addMissingFieldsInInput(keyMap, key);
                            }
                        }
                    }

                    for (String key : expectedMap.keySet()) {

                        if (ignoreFieldsInInput != null && ignoreFieldsInInput.contains(key))
                            continue;

                        if (!actualMap.containsKey(key)) {
                            result.addMissingFieldsInJson(keyMap, key);
                        }
                    }
                }
            } else {
                result.addMissingRecordsInJson(keyMap);
            }
        }

        if (isJsonArray) {
            Object recordsInJsonInitial = (List<?>) JsonPath.read(document, path);
            List<Map<String, Object>> recordsInJson = new ArrayList<>();
            if (((List<?>) recordsInJsonInitial).get(0) instanceof List) {
                recordsInJson = (List<Map<String, Object>>) ((List<?>) recordsInJsonInitial).get(0);
            } else {
                recordsInJson = (List<Map<String, Object>>) recordsInJsonInitial;
            }

            for (Map<String, Object> record : recordsInJson) {
                HashMap<String, Object> recordKeyMap = new HashMap<>();
                Object id = record.get(identifier);
                if (id != null) {
                    recordKeyMap.put(identifier, id.toString());
                    if (!expectedKeys.contains(recordKeyMap)) {
                        result.addMissingRecordsInInput(recordKeyMap);
                    }
                }
            }
        }
        return result;
    }
}
