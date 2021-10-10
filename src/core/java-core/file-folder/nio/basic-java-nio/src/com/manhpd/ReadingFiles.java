package com.manhpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ReadingFiles {

    public static void main(String[] args) {
//        String filePath = "files/sample-utf-8.txt";
        String filePath = "files/sample-other-encoding.txt";
        readFiles(filePath, StandardCharsets.ISO_8859_1);
    }

    public static void readFiles(String filePath, Charset charset) {
        Path path = Path.of(filePath);
        boolean isExistedFile = Files.exists(path);
        System.out.println("Exists: " + isExistedFile);

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line = "";
            do {
                line = reader.readLine();
                System.out.println("Content: " + line);
            } while (Objects.nonNull(line));
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }
    }

}
