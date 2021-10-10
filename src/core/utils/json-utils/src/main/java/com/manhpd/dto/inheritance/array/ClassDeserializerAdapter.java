package com.manhpd.dto.inheritance.array;

import com.google.gson.*;
import com.manhpd.dto.inheritance.BaseClass;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Note that if in json string, there is a type field, then when deserialized json, we would get this type field.
 *
 * https://stackoverflow.com/questions/16476513/class-a-declares-multiple-json-fields
 * https://stackoverflow.com/questions/21626690/gson-optional-and-required-fields
 *
 */
public class ClassDeserializerAdapter implements JsonDeserializer<BaseClass> {

    private String typeName;

    private Gson gson;

    private Map<String, Class<? extends BaseClass>> classTypeRegistry;

    public ClassDeserializerAdapter(String typeName) {
        this.typeName = typeName;
        gson = new Gson();
        classTypeRegistry = new HashMap<>();
    }

    public void registerClassType(String classTypeName, Class<? extends BaseClass> classType) {
        // registering Types to Strings
        classTypeRegistry.put(classTypeName, classType);
    }

    @Override
    public BaseClass deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement typeElement = jsonObject.get(typeName);
        String method = typeElement.getAsString();
        Class<? extends BaseClass> classType = classTypeRegistry.get(method);
        BaseClass result = gson.fromJson(json, classType);

        return result;
    }
}
