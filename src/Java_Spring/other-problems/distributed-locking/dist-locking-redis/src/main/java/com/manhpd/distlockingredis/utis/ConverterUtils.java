package com.manhpd.distlockingredis.utis;

import com.google.gson.Gson;
import com.manhpd.distlockingredis.dto.EmployeeDto;

public class ConverterUtils {

    public static <T> String toString(T t) {
        Gson gson = new Gson();
        return gson.toJson(t);
    }

    public static void main(String[] args) {
        EmployeeDto dto = new EmployeeDto(1, "Bill", "Clinton", 68);
        String str = ConverterUtils.toString(dto);
        System.out.println(str);
    }

}
