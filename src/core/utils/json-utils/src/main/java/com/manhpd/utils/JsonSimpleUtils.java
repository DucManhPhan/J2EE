package com.manhpd.utils;

import com.manhpd.dto.JsonType;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * https://github.com/fangyidong/json-simple/tree/master/src/main/java/org/json/simple
 *
 */
public class JsonSimpleUtils {

    /**
     * Accept format json with object and array
     * @param data
     * @return
     * @throws ParseException
     */
    public static boolean isJsonValid(String data) throws ParseException {
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(data);
            return true;
        } catch (ParseException | ClassCastException exception) {
            System.out.println("Can not convert json data to JSONObject");
            try {
                JSONArray jsonArray = (JSONArray) parser.parse(data);
                return true;
            } catch (ParseException e) {
                System.out.println("Can not convert json data to JSONArray");
                return false;
            }
        }
    }

    public static void writeFile(String data, String path) throws ParseException, FileNotFoundException {
        boolean isJsonValid = JsonSimpleUtils.isJsonValid(data);
        if (!isJsonValid) {
            return;
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(data);

        try (BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
            bf.write(jsonObject.toString());
        } catch (IOException ex) {
            System.out.println("Exception when writing data to file in " + path + " : " + ex.toString());
        }

        System.out.println("Successfully write file");
    }

    public static JSONObject readFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("data.json");

        Object jsonObj = parser.parse(reader);

        return (JSONObject) jsonObj;
    }

    /**
     * Get data based on field path in json data
     *
     * @param data
     * @param path
     * @return
     */
    public static Object getDataBasedPathWithJsonSimple(JSONObject data, String path) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }

        // prepare for fields
        List<String> fieldPaths = new ArrayList<>();
        if (!path.contains("/")) {
            fieldPaths = Arrays.asList(path);
        } else {
            fieldPaths = Arrays.asList(path.split("/"));
        }

        // get specific field
        final Object[] currentDataField = { data };
        fieldPaths.stream().forEach(field -> {
            Object tmpObject = JsonSimpleUtils.getDataOfFieldWithJsonSimple(currentDataField[0], field);
            currentDataField[0] = tmpObject;
        });

        return currentDataField[0];
    }

    /**
     * convert string data to JSONObject
     *
     * @param output
     * @return
     */
    public static JSONObject toJsonObjectWithJsonSimple(String output) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;

        if (StringUtils.isNotEmpty(output)) {
            try {
                json = (JSONObject) parser.parse(output);
            } catch (ParseException e) {
                System.out.println("Can't pare String to JsonObject");
            }
        }

        return json;
    }

    private static Object getDataOfFieldWithJsonSimple(Object json, String field) {
        JsonType type = JsonSimpleUtils.getJsonTypeWithJsonSimple(json);
        switch (type) {
            case JSON_OBJECT:
                JSONObject jsonObject = (JSONObject) json;
                return jsonObject.get(field);

            case JSON_ARRAY:
                JSONArray jsonArray = (JSONArray) json;
                for (Object item : jsonArray) {
                    JSONObject tmp = (JSONObject) item;
                    return tmp.get(field);
                }

            default:
                break;
        }

        return null;
    }

    /**
     * Check type of Object with json data
     * @param json
     * @return
     */
    private static JsonType getJsonTypeWithJsonSimple(Object json) {
        if (json instanceof JSONObject) {
            return JsonType.JSON_OBJECT;
        } else if (json instanceof JSONArray) {
            return JsonType.JSON_ARRAY;
        } else {
            return JsonType.NO_JSON_TYPE;
        }
    }

    /**
     * Iterate JSONObject by using Java 8 stream
     *
     * @param jsonObject
     */
    public static void printJsonObjectWithJsonSimple_stream(JSONObject jsonObject) {
        jsonObject.keySet().forEach(key -> {
            Object value = jsonObject.get(key);
            System.out.println("Key: " + key.toString() + ", Value: " + value.toString());
        });
    }

    /**
     * Iterate JSONObject by using for each
     *
     * @param jsonObject
     */
    public static void printJsonObjectWithJsonSimple_for(JSONObject jsonObject) {
        for (Object key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            System.out.println("Key: " + key.toString() + ", Value: " + value.toString());
            if (value instanceof JSONObject) {
                printJsonObjectWithJsonSimple_for((JSONObject) value);
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                jsonArray.stream().forEach(item -> {
                    printJsonObjectWithJsonSimple_for(jsonObject);
                });
            }
        }
    }

}
