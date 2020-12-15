package com.manhpd.first_way;

import com.manhpd.utils.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UsingScannerWay {

    private String path;

    public UsingScannerWay() {
        this(Constants.SMALL_FILE_DATA_PATH);
    }

    public UsingScannerWay(String path) {
        this.path = path;
    }

    public void readFile() {
        try(Scanner scanner = new Scanner(new FileReader(new File(this.path)))) {
            String currentLine = "";
            int totalNumOfLines = 0;

            while ((currentLine = scanner.nextLine()) != null) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
