package com.manhpd.utils;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {

    public static void create() throws UnsupportedEncodingException, FileNotFoundException {
        double wantedSize = Double.parseDouble(System.getProperty("size", "1.5"));

        Random random = new Random();
        File file = new File("large-random-number.txt");
        long start = System.currentTimeMillis();
        PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")), false);
        int counter = 0;
        while (true) {
            String sep = "";
            for (int i = 0; i < 100; i++) {
                int number = random.nextInt(1000) + 1;
                writer.print(sep);
                writer.print(number / 1e3);
                sep = " ";
            }

            writer.println();
            // Check to see if the current size is what we want it to be
            if (++counter == 20000) {
                System.out.printf("Size: %.3f GB%n", file.length() / 1e9);
                if (file.length() >= wantedSize * 1e9) {
                    writer.close();
                    break;
                } else {
                    counter = 0;
                }
            }
        }

        long time = System.currentTimeMillis() - start;
        System.out.printf("Took %.1f seconds to create a file of %.3f GB", time / 1e3, file.length() / 1e9);
    }

    /**
     * Read file by using Scanner
     * Disadvantage:
     * https://stackoverflow.com/questions/19486077/java-fastest-way-to-read-through-text-file-with-2-million-lines
     * https://www.novixys.com/blog/java-reading-large-file-efficiently/
     * https://www.baeldung.com/java-read-lines-large-file
     *
     * Format:
     * 1. Which Asian's location does Viet nam belong to ?
     * A East Asia             C South East Asia
     * B American              D Russia
     * Answer - B
     *
     * 2. What do you like ?
     * A Banana                       C Cat
     * B Paper                        D Dog
     * Answer - D
     *
     * @param path
     * @throws FileNotFoundException
     */
    public static void readByScanner(String path) throws FileNotFoundException {
        if (path.equals("")) {
            return;
        }

        FileInputStream fileStream = new FileInputStream(path);
        Scanner scanner = new Scanner(fileStream);

        char solution = 'E';
        char answer = 'F';
        String line = null;

        while (scanner.hasNextLine() || line != "") {
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            } else {
                line = "";  // at the end of file
            }

            // get solution for this question
            if (line.contains("Answer - ")) {
                Pattern pattern = Pattern.compile("\\s*([A-D])$");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    solution = matcher.group(1).charAt(0);
                }

                continue;
            }

            // check user's answer at the end of question
            if (line.compareTo("") == 0) {
                do {
                    System.out.println("Input your answer for question: ");
                    Scanner reader = new Scanner(System.in);
                    answer = reader.next(".").charAt(0);
                } while (answer != solution);
            }

            System.out.println(line);
        }
    }

    public static void readByBufferReader(String path) {

    }

    public static void write(String path) {

    }

}
