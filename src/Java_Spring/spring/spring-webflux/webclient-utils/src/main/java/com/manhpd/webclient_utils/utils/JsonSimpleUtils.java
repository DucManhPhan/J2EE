package com.manhpd.webclient_utils.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * json-simple is that it is also JDK 1.2 compatible, 
 * which means we can use it on a legacy project which is not yet in Java 5.
 * 
 */
public class JsonSimpleUtils {

	public static JSONObject toJsonObject(String data) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		return (JSONObject) jsonParser.parse(data);
	}

	public static JSONArray toJsonCollection(String data) {
		return null;
	}

	public static String toString(JSONObject json) {
		return null;
	}

	public static String toString(JSONArray json) {
		return null;
	}
}
