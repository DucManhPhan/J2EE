package com.manhpd;


import com.manhpd.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class App {

    private static final String path = "./large-random-number.txt";

    public static void main( String[] args ) throws IOException {
        // Creat large file with numbers
//        FileUtils.create();

        // Calculate time for reading file
//        File file = new File(path);
//        if (!file.exists()) {
//            return;
//        }

//        long start = System.currentTimeMillis();
////        FileUtils.readByStreams(path);
//        FileUtils.readByBufferReader(path);
//        long end = System.currentTimeMillis();
//
//        long time = System.currentTimeMillis() - start;
//        System.out.printf("Took %.1f seconds to read a file of %.3f GB", time / 1e3, file.length() / 1e9);

        // Producer -Consumer model for reading file
        Producer producer = new Producer();
		producer.start();

		new Consumer(producer).start();
    }
}
