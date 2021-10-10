package com.manhpd.utils;

import java.io.InputStream;

public class FileUtil {

    public static InputStream getFileFromResources(String fileName) {
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        InputStream resource = classLoader.getResourceAsStream(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return resource;
        }
    }

}
