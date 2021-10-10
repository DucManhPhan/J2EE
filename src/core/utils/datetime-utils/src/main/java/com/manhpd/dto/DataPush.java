package com.manhpd.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.manhpd.utils.Constants;
import com.manhpd.utils.DataUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPush {

    private Integer type;

    private String title;

    private String body;

    private String message;

    private String feature_id;  // possible

    private String description;

    private String action_button;

    private String object_id;

    private String object_code; // possible

    private String promotion_id;

    private String id;

    private String link; // possible

    private String linkRedirect;

    private String isAuth;  // possible

    private String isRediect;   // possible

    private String itemId;    // possible

    private String campaignCode;

    private String campaignPush;

    private String flash_sale_id;   // possible

    private String flash_sale_link; // possible

    public static DataPush of(String data) {
        DataPush result = new DataPush();
        if (DataUtils.isEmpty(data)) {
            return result;
        }

        try {
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

                result.setValueForField(field, entry);
            }
        } catch(NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            System.out.println("Error when set data for DataPush's field: " + ex.getMessage());
        } catch(JsonSyntaxException jsonEx) {
            System.out.println("Error when parsing data" + jsonEx.getMessage());
        }

        return result;
    }

    private void setValueForField(Field field, Map.Entry<String, JsonElement> entry) throws IllegalArgumentException, IllegalAccessException {
        Class fieldType = field.getType();
        JsonElement value = entry.getValue();
        if (fieldType.equals(Integer.class)) {
            if (value != null) {
                field.set(this, entry.getValue().getAsInt());
            } else {
                field.set(this, Constants.ERROR_INT_FIELD);
            }
        } else if (fieldType.equals(String.class)) {
            if (value == null || value.isJsonNull()) {
                field.set(this, Constants.ERROR_STRING_FIELD);
            } else {
                field.set(this, entry.getValue().getAsString());
            }
        }
    }

}

