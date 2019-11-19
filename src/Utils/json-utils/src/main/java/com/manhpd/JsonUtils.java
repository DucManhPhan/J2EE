package com.manhpd;

import com.google.gson.*;
import com.manhpd.dto.JsonType;
import com.manhpd.dto.Person;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static String createJsonSample() {
        return "{ \"pageInfo\": { \"pageName\": \"abc\", \"pagePic\": \"http://example.com/content.jpg\" }, \"posts\": [ { \"post_id\": \"123456789012_123456789012\", \"actor_id\": \"1234567890\", \"picOfPersonWhoPosted\": \"http://example.com/photo.jpg\", \"nameOfPersonWhoPosted\": \"Jane Doe\", \"message\": \"Sounds cool. Can't wait to see it!\", \"likesCount\": \"2\", \"comments\": [\"Hello\", \"Hi\", \"I am Tony Stark\", \"Obama president\"], \"timeOfPost\": \"1234567890\" } ] }";
    }

    /**
     * Use Gson
     *
     * @param object
     * @return
     */
    public static String toJsonStringWithGson(Object object) {
        if (object == null) {
            return "";
        }

        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Person toPersonObjectWithGson(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(data, Person.class);
    }

//    public static String getDataBasedOnFields(String data, String path) {
//        String[] fields = {};
//        if (path.contains("/")) {
//            fields = path.split("/");
//        } else {
//            fields = new String[]{ path };
//        }
//
//        JsonElement tmpJsonElement = new JsonParser().parse(data);
//        for (String item : fields) {
//            JsonObject jsonObject = tmpJsonElement.getAsJsonObject();
//
//            JsonArray arrayData = jsonObject.getAsJsonArray(path);
//        }
//
//    }

    /**
     * Use json-simple
     *
     * @param data
     * @param path
     * @return
     */
    public static Map getDataBasedPathWithJsonSimple(JSONObject data, String path) {
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

        });

        return null;
    }

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
        JsonType type = JsonUtils.getJsonTypeWithJsonSimple(json);
        switch (type) {
            case JSON_OBJECT:
                JSONObject jsonObject = (JSONObject) json;
                break;

            case JSON_ARRAY:

                break;

            default:
                break;
        }

        return null;
    }

    private static JsonType getJsonTypeWithJsonSimple(Object json) {
        if (json instanceof JSONObject) {
            return JsonType.JSON_OBJECT;
        } else if (json instanceof JSONArray) {
            return JsonType.JSON_ARRAY;
        } else {
            return JsonType.NO_JSON_TYPE;
        }
    }

}
