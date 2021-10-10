package com.manhpd.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manhpd.utils.StringUtils;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author ManhPD
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPush {
    
    private Integer type;
    
    private String title;
    
    private String body;
    
    private String message;
    
    private String feature_id;  
    
    private String description;
    
    private String action_button;
    
    private String object_id;
    
    private String object_code;
    
    private String promotion_id;
    
    private String id;
    
    private String linkRedirect;
    
    private String isAuth;
    
    private String isRediect;
    
    private String itemId;
    
    private String campaignCode;
    
    private String campaignPush;
    
    private String flash_sale_id;
    
    private String flash_sale_link;
    
    public static DataPush of(String data) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        DataPush result = new DataPush();
        if (StringUtils.isEmpty(data)) {
            return result;
        }

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonDataPush = jsonParser.parse(data).getAsJsonObject();
        if (jsonDataPush == null) {
            return result;
        }
        
        Class dataPushClass = result.getClass();
        Set<Map.Entry<String, JsonElement>> entrySet = jsonDataPush.entrySet();
        for(Map.Entry<String,JsonElement> entry : entrySet){
            String fieldName = entry.getKey();
            Field field = dataPushClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            
            Class fieldType = field.getType();
            System.out.println("Type of above field is: " + fieldType.getName());
            if (field.getType().equals(Integer.class)) {
                field.set(result, entry.getValue().getAsInt());
            } else if (field.getType().equals(String.class)) {
                field.set(result, entry.getValue().getAsString());
            } else {
                System.out.println("Do not exist this type: " + field.getType().getName());
            }
        }

        return result;
    }
}