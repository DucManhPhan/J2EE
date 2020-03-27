package com.manhpd;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileUtils {

    public static boolean isExistFile(String path) {
            File file = new File(path);
            return file.exists();
    }

    public static String readContentFile(String path) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bf = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }

        return sb.toString();
    }

}
