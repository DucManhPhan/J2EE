package com.manhpd.webclient_utils.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manhpd.webclient_utils.model.User;

public class JsonGsonUtils {

	@SuppressWarnings("deprecation")
	public static JsonObject toJsonObject(String data) {
		// 1st way: Use JsonParser
		JsonParser jsonParser = new JsonParser();
		JsonObject firstJson = jsonParser.parse(data).getAsJsonObject();

		Assert.isTrue(firstJson.isJsonObject());
		Assert.isTrue(firstJson.get("name").getAsString().equals("Baeldung"));
		Assert.isTrue(firstJson.get("java").getAsBoolean() == true);
		
		// 2nd way: Use fromJson() method
		Gson gson = new Gson();
		JsonObject secondJson = gson.fromJson(data, JsonObject.class); 

		return firstJson;
	}

	public static JsonArray toJsonCollection(String data) {
		JsonParser jsonParser = new JsonParser();
		JsonArray json = jsonParser.parse(data).getAsJsonArray();

		return json;
	}

	public static String toString(JsonObject json) {
		Gson gson = new Gson();
		return gson.toJson(json);
	}

	public static String toString(JsonArray json) {
		Gson gson = new Gson();
		return gson.toJson(json);
	}

	/**
	 * /* put string into file jsonFileArray.json
	 * [{"username":"Hello","email":"hello@email.com","credits"
	 * :"100","twitter_username":""},
	 * {"username":"Goodbye","email":"goodbye@email.com"
	 * ,"credits":"0","twitter_username":""},
	 * {"username":"mlsilva","email":"mlsilva@email.com"
	 * ,"credits":"524","twitter_username":""},
	 * {"username":"fsouza","email":"fsouza@email.com"
	 * ,"credits":"1052","twitter_username":""}]
	 * 
	 * @param json
	 * @return
	 */
	public static List<User> toUsers(String json) {
		List<User> users = new ArrayList<>();
		Gson gson = new Gson();

		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader("C:\\Temp\\jsonFileArray.json"));
			JsonArray jsonArray = new JsonParser().parse(bufferReader).getAsJsonArray();

			for (int i = 0; i < jsonArray.size(); i++) {
				JsonElement str = jsonArray.get(i);
				User user = gson.fromJson(str, User.class);
				users.add(user);

				System.out.println(user);
				System.out.println(str);
				System.out.println("-----------------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return users;
	}
}
