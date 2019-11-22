package com.manhpd.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileUtils {

    private static Logger logger = LogManager.getLogger(FileUtils.class.getName());

    private static final ExecutorService deleteService = Executors.newSingleThreadExecutor();

    public static void removeFileSync(String pathFile) {
        File file = new File(pathFile);
        FileUtils.removeFileSync(file);
    }

    public static void removeFileSync(File file) {
        if (file.delete()) {
            System.out.println("Remove file successfully");
        } else {
            System.out.println("Failed to delete file");
        }
    }

    /**
     * 1st - delete all files before deleting folder - recursive that have no good performance
     * 2nd - use FileUtils of org.apache.commons.io
     *
     * @param pathFolder
     */
    public static void removeFolderSync(String pathFolder) {
        File folder = new File(pathFolder);
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                // remove file in this folder
                FileUtils.removeFolderSync(file.getAbsolutePath());
            }

            // remove empty folder after removing all file in it
            FileUtils.removeFileSync(file);
        }
    }

    /**
     * 1st - Use @Asynchronous annotation
     * 2nd - Create a thread to delete file
     * 3rd - Use Java NIO package
     * @param file
     */
    public static void removeFileAsync(File file) {
        // 2nd - Create a thread to delete file
//        if (file == null) {
//            return;
//        }
//
//        deleteService.submit(new Runnable() {
//            @Override
//            public void run() {
//                file.delete();
//            }
//        });

        // 3rd - Use Java NIO package
        // remember: the path parameter is an instance of java.nio.file.Path
        Path filePath = Paths.get(file.getAbsolutePath());
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            logger.error("Do not delete file using NIO package with " + filePath.getFileName());
            e.printStackTrace();
        }
    }

    public static void removeFileAsync(String pathFile) {
        File file = new File(pathFile);
        FileUtils.removeFileAsync(file);
    }

    public static void listAllFiles(String path) {
        File folder = new File(path);
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                System.out.println("Folder: " + fileEntry.getPath());
            } else {
                System.out.println(fileEntry.getAbsoluteFile());
            }
        }
    }

    public static List<File> listDrivers() {
        File[] drivers = File.listRoots();
        for (File driver : drivers) {
            System.out.println(driver.getAbsolutePath());
        }

        return Arrays.asList(drivers);
    }

    public static void listAllFilesOnConsole(String path) {
        File dir = new File(path);
        FileUtils.listChild(dir, 0);
    }

    private static void listChild(File file, int level) {
        if (file.isDirectory()) { // Dừng nếu là tập tin
            System.out.println(getPadding(level) + " - " + file.getName());
            File[] children = file.listFiles();
            for (File child : children) {
                FileUtils.listChild(child, level + 1); // Gọi đệ quy
            }
        } else {
            System.out.println(getPadding(level) + " + " + file.getName());
        }
    }

    private static String getPadding(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= level; i++) {
            sb.append("    "); // Thêm dấu tab.
        }
        return sb.toString();
    }

}
