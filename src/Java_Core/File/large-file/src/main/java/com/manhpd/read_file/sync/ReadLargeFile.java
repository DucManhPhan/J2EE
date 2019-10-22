package com.manhpd.read_file.sync;

import java.io.*;

public class ReadLargeFile {

    private String path;

    public ReadLargeFile() {
        this("large-random-number.txt");
    }

    public ReadLargeFile(String path) {
        this.path = path;
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))) {
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
