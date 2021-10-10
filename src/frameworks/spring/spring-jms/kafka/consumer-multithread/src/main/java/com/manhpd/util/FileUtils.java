package com.manhpd.util;

import com.manhpd.kafka.ConsumerThreads;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class FileUtils {

    public static String getAbsolutePathOf(String fileName) throws URISyntaxException {
        URL res = ConsumerThreads.class.getClassLoader().getResource(fileName);
        File file = Paths.get(res.toURI()).toFile();
        String path = file.getAbsolutePath();

        return path;
    }

}
