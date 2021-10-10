package com.manhpd.dto.inheritance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manhpd.dto.inheritance.array.ClassDeserializerAdapter;
import com.manhpd.dto.inheritance.array.GsonArrayBaseclass;
import com.manhpd.dto.inheritance.object.JsonDeserializerWithInheritance;

import java.util.ArrayList;
import java.util.List;

public class TestGsonInheritance {

    public static void main(String[] args) {
        // deserialize object with gson
//        DerivedClass1 derivedClass1 = new DerivedClass1(5, 10);
//
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(BaseClass.class, new JsonDeserializerWithInheritance<BaseClass>());
//        Gson gson = builder.create();
//
//        String derivedClass1Json = gson.toJson(derivedClass1);
//        System.out.println("Derived class 1's data is: " + derivedClass1Json);
//
//        BaseClass maybeDerivedClass1 = gson.fromJson(derivedClass1Json, BaseClass.class);
//        System.out.println(maybeDerivedClass1.toString());

        // Deserialize with array base class
        parseInheritanced();
    }

    private static void parseInheritanced() {
        List<BaseClass> baseClassList = new ArrayList<BaseClass>() {{
            add(new BaseClass(1));
            add(new DerivedClass1(2, 5));
            add(new DerivedClass1(3, 10));
            add(new DerivedClass2(4, 15));
        }};

        GsonArrayBaseclass arrayBaseclass = new GsonArrayBaseclass(baseClassList);
        Gson gson = new Gson();
        String json = gson.toJson(arrayBaseclass);
        System.out.println("Json of array base class is: " +json);

        // Deserialize
        ClassDeserializerAdapter deserializer = new ClassDeserializerAdapter("type");
        // registering each Type into the Deserializer's HashMap (key-value pair), where the key (String) must be carried by the object (you can find it in the BaseClass, called "Method")
        deserializer.registerClassType("DerivedClass1", DerivedClass1.class);
        deserializer.registerClassType("DerivedClass2", DerivedClass2.class);
        deserializer.registerClassType("BaseClass", BaseClass.class);
        Gson gsonB = new GsonBuilder().registerTypeAdapter(BaseClass.class, deserializer).create();

        GsonArrayBaseclass deserialized = gsonB.fromJson(json, GsonArrayBaseclass.class);
        System.out.println("Array of gson base class is: " + deserialized.toString());
    }

}
