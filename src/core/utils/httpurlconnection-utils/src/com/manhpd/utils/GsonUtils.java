package com.manhpd.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.manhpd.dto.JsonType;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class GsonUtils {

    /**
     * Accept format json with object and array
     *
     * @param jsonInString
     * @return
     */
    public static boolean isJsonValid(String jsonInString) {
        Gson gson = new Gson();
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch(JsonSyntaxException ex) {
            return false;
        }
    }

    public static String createJsonSample() {
        return "{ \"pageInfo\": { \"pageName\": \"abc\", \"pagePic\": \"http://example.com/content.jpg\" }, \"posts\": [ { \"post_id\": \"123456789012_123456789012\", \"actor_id\": \"1234567890\", \"picOfPersonWhoPosted\": \"http://example.com/photo.jpg\", \"nameOfPersonWhoPosted\": \"Jane Doe\", \"message\": \"Sounds cool. Can't wait to see it!\", \"likesCount\": \"2\", \"comments\": [\"Hello\", \"Hi\", \"I am Tony Stark\", \"Obama president\"], \"timeOfPost\": \"1234567890\" } ] }";
    }

    /**
     * Convert Java object to json string
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        if (object == null) {
            return "";
        }

        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static JsonElement getDataBasedOnFields(String data, String path) {
        String[] fields = {};
        if (path.contains("/")) {
            fields = path.split("/");
        } else {
            fields = new String[]{ path };
        }

        JsonElement tmpJsonElement = new JsonParser().parse(data);
        JsonElement[] elements = { tmpJsonElement };
        Arrays.stream(fields).forEach(field -> {
            JsonElement tmp = GsonUtils.getDataOfField(elements[0], field);
            elements[0] = tmp;
        });

        return elements[0];
    }

    /**
     * Convert Json String array to Java String Array
     *
     * @return
     */
    public static List<String> toStringArray(String jsonStringArray) {
        Gson converter = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();

        return converter.fromJson(jsonStringArray, type);
    }

    /**
     * Convert Json Integer array to Java Integer array
     *
     * @param jsonIntegerArray
     * @return
     */
    public static List<Integer> toIntegerArray(String jsonIntegerArray) {
        Gson converter = new Gson();
        Type type = new TypeToken<List<Integer>>(){}.getType();

        return converter.fromJson(jsonIntegerArray, type);
    }

    /**
     * Convert List of string to JsonArray of Gson
     * @param list
     * @return
     */
    public static JsonArray toJsonArray(List<String> list) {
        Gson converter = new Gson();
        String json = converter.toJson(list);
        Type type = new TypeToken<JsonArray>(){}.getType();

        return converter.fromJson(json, type);
    }

    private static JsonType getJsonType(Object json) {
        if (json instanceof JsonObject) {
            return JsonType.JSON_OBJECT;
        } else if (json instanceof JsonArray) {
            return JsonType.JSON_ARRAY;
        } else {
            return JsonType.NO_JSON_TYPE;
        }
    }

    private static JsonElement getDataOfField(JsonElement json, String field) {
        JsonType type = GsonUtils.getJsonType(json);
        switch (type) {
            case JSON_OBJECT:
                JsonObject jsonObject = json.getAsJsonObject();
                return jsonObject.get(field);

            case JSON_ARRAY:
                JsonArray jsonArray = json.getAsJsonArray();
                for (Object item : jsonArray) {
                    JsonObject tmp = (JsonObject) item;
                    return tmp.get(field);
                }

            default:
                break;
        }

        return null;
    }

    /**
     * create json object with format
     * Ex: "{"3044021":1}"
     *
     * @param id
     * @return
     */
    public static String createJsonObjectBasedField(String id) {
        JsonObject notificationsJson = new JsonObject();
        notificationsJson.addProperty(id, "0");
        String newNotify = notificationsJson.toString();

        return newNotify;
    }

    public static String convertListStringToJson(List<String> data) {
        Gson converter = new Gson();
        return converter.toJson(data);
    }

}
