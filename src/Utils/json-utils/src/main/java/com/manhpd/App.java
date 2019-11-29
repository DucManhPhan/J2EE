package com.manhpd;

import com.google.gson.JsonElement;
import com.manhpd.utils.GsonUtils;
import com.manhpd.utils.JacksonUtils;
import com.manhpd.utils.JsonSimpleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        String data = GsonUtils.createJsonSample();
//        String comments = JsonUtils.getDataBasedOnField(data, "comments");
//
//        System.out.println(comments);

        // get specific field path
//        JSONObject jsonObject = JsonUtils.toJsonObjectWithJsonSimple(data);
//        JSONArray comments = (JSONArray) JsonUtils.getDataBasedPathWithJsonSimple(jsonObject, "posts/comments");
//        System.out.println(comments.toString());

        // convert Json string array to Java array of string
//        String jsonStringArray = "[\"JSON\",\"To\",\"Java\"]";
//        List<String> lst = JsonUtils.toStringArray(jsonStringArray);
//        System.out.println(lst.toString());
//
//        System.out.println(JsonUtils.toJsonArray(lst));

        // convert Json integer array to Java array of integer
//        String jsonIntegerArray = "[101,201,301,401,501]";
//        List<Integer> lst = GsonUtils.toIntegerArray(jsonIntegerArray);
//        System.out.println(lst);

        // Use gson to get spedific path's field
//        JsonElement comments = GsonUtils.getDataBasedOnFields(data, "posts/comments");
//        System.out.println(comments.toString());

        // check json is valid
//        String tmp = "[\"hello\", \"hi\", \"bongzuo\"]";
//        System.out.println("Is valid json: " + JsonSimpleUtils.isJsonValid(tmp));

        // write file
//        JsonSimpleUtils.writeFile(data, "./data.json");

//        System.out.println(GsonUtils.createJsonObjectBasedField(Integer.toString(3044021)));

        String nameOfClass = "This is main class.";
        logger.info("{}", nameOfClass);
    }

}
