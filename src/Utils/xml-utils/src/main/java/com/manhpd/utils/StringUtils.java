package com.manhpd.utils;

public class StringUtils {

    public static boolean isValid(String content) {
        return content != null && !(content.trim().equals(""));
    }

}
