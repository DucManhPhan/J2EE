package com.manhpd;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTreePattern {

    public static void main(String[] args) throws IOException {
        String filePath = "files/media";
        walkFileTreePattern(filePath);
    }

    public static void walkFileTreePattern(String filePath) throws IOException {
        Path path = Path.of(filePath);
        boolean isDirectory = Files.isDirectory(path);
        System.out.println("Directory: " + isDirectory);

        var visitor = new FileVisitor<Path>() {
            private long countFiles = 0L;

            private long countDirs = 0L;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                ++countDirs;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                ++countFiles;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        };

        Files.walkFileTree(path, visitor);
        System.out.println("The number of files: " + visitor.countFiles);
        System.out.println("The number of directories: " + visitor.countDirs);
    }

}
