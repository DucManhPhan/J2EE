package com.manhpd.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JacksonUtils {

    /**
     * Accept only format json with object
     *
     * @param data
     * @return
     */
    public static boolean isJsonValid(String data) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(data);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Map<String, Object> readJsonFile(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(path);
        Map<String, Object> mapObject = objectMapper.readValue(jsonFile, new TypeReference<Map<String, Object>>(){});

        return mapObject;
    }

}
