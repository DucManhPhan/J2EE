package com.manhpd.second_way;

import java.io.*;

public class UsingBufferedReaderWay {

    private String path;

    public UsingBufferedReaderWay() {

    }

    public UsingBufferedReaderWay(String path) {
        this.path = path;
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(this.path)))) {
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
