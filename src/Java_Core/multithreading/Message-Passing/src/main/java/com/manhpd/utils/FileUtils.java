package com.manhpd.utils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileUtils {

    public static void create() throws UnsupportedEncodingException, FileNotFoundException {
        double wantedSize = Double.parseDouble(System.getProperty("size", "0.5"));

        Random random = new Random();
        File file = new File("large-random-number.txt");
        long start = System.currentTimeMillis();
        PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")), false);
        int counter = 0;
        while (true) {
            int number = random.nextInt(10000) + 1;
            writer.print(number);

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
        String line = "";

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

    /**
     * We need to use BufferReader.readLine() - it can read millions of lines a second
     * BufferReader has high performance that is better than Scanner
     * The default buffer size of it is 8192 bytes
     * We should use BufferReader to read large file
     *
     * https://stackoverflow.com/questions/4638974/what-is-the-buffer-size-in-bufferedreader
     *
     * @param path
     */
    public static void readByBufferReader(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void readByStreams(String path) throws IOException {
        Files.lines(Paths.get(path))
             .forEach(line -> System.out.println(line));

//        List<String> contents = Files.lines(Paths.get(path))
//                                     .filter(line -> line.contains("1"))
//                                     .collect(Collectors.toList());
    }

    public static void readBinaryFile(String path) throws FileNotFoundException, IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(path)));
        byte[] buf = new byte[4096];
        int len;

        while ((len = bufferedInputStream.read(buf)) != -1) {
            // do something with data
        }
    }

    /**
     * If file's size is small, we can use nio, or MappedByteBuffer
     *
     * @param path
     */
    public static String readWholeFile(String path) throws IOException {
        File file = new File(path);
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
        MappedByteBuffer buffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());

        randomAccessFile.close();
        return new StringBuilder(StandardCharsets.UTF_8.decode(buffer)).toString();
    }

    public static void write(String path) {

    }

}
