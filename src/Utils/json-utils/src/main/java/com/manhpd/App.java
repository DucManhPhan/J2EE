package com.manhpd;

import com.manhpd.dto.Person;
import org.json.simple.JSONObject;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        String data = JsonUtils.createJsonSample();
//        String comments = JsonUtils.getDataBasedOnField(data, "comments");
//
//        System.out.println(comments);

        JSONObject jsonObject = JsonUtils.toJsonObjectWithJsonSimple(data);
        JSONObject comments = (JSONObject) JsonUtils.getDataBasedPathWithJsonSimple(jsonObject, "posts/comments");
        System.out.println(comments.toString());
    }

}
