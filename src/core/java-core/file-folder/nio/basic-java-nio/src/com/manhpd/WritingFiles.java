package com.manhpd;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WritingFiles {

    public static void main(String[] args) {
        String filePath = "files/new-sample.txt";
        writeFiles("Hello, world!!!", filePath, StandardCharsets.UTF_8);
    }

    public static void writeFiles(String content, String filePath, Charset charset) {
        Path path = Path.of(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            writer.write(content);
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }
    }

}
