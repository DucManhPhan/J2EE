package com.manhpd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class WalkPattern {

    public static void main(String[] args) throws IOException {
        String filePath = "files/media";
        walkPattern(filePath);
    }

    public static void walkPattern(String filePath) throws IOException {
        Path path = Path.of(filePath);
        boolean isDirectory = Files.isDirectory(path);
        System.out.println("Directory: " + isDirectory);

        Stream<Path> paths = Files.walk(path);
        long count = paths.count();
        long countDirs = Files.walk(path).filter(Files::isDirectory).count();
        long countFiles = Files.walk(path).filter(Files::isRegularFile).count();

        System.out.println("Count = " + count);
        System.out.println("The number of directories: " + countDirs);
        System.out.println("The number of files: " + countFiles);
    }

}
