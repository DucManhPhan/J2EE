package com.manhpd.creation;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreationOfStream {

    public static void main(String[] args) throws IOException {
//        createEmptyStream();

//        createStreamByCollection();

//        createStreamByArray();

//        createStreamWithBuilder();

//        createStreamWithIteration();

//        createStreamWithBufferedReader();

//        createStreamWithRange();

//        createStreamWithFileNio();

        checkLaziLoadOfIntermediateOperations();

//        createStreamWithRandom();
    }

    private static void createEmptyStream() {
        Stream<String> streamEmpty = Stream.empty();
        System.out.println(streamEmpty);
    }

    private static void createStreamByCollection() {
        Collection<Integer> collection = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
            add(4);
        }};

        Stream<Integer> intStream = collection.stream();
        intStream.forEach(item -> System.out.println(item));
    }

    private static void createStreamByArray() {
        Integer[] ints = {1, 2, 3, 4, 5};
        Stream<Integer> intStream = Arrays.stream(ints);
        intStream.forEach(item -> System.out.println(item));
    }

    private static void createStreamWithBuilder() {
        Stream<Integer> intStream = Stream.<Integer>builder().add(1)
                                                                 .add(2)
                                                                 .add(3)
                                                                 .add(4)
                                                                 .add(5)
                                                                 .build();
        intStream.forEach(item -> System.out.println(item));
    }

    private static void createStreamWithIteration() {
        Stream<Integer> intStream = Stream.iterate(10, n -> n + 2).limit(10);
        intStream.forEach(item -> System.out.println(item));
    }

    private static void createStreamWithBufferedReader() throws FileNotFoundException {
        String file_path = ".\\data.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file_path)));
        Stream<String> lineStream = bufferedReader.lines();

        lineStream.forEach(line -> System.out.println(line));
    }

    private static void createStreamWithRange() {
        IntStream intStream = IntStream.range(5, 10);
        intStream.forEach(item -> System.out.println(item));
    }

    private static void createStreamWithFileNio() throws IOException {
        Path path = Paths.get("./data.txt");
//        Stream<String> contents = Files.lines(path);
        Stream<String> contents = Files.lines(path, Charset.forName("UTF-8"));
        contents.forEach(item -> System.out.println(item));
    }

    private static void checkLaziLoadOfIntermediateOperations() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3", "abc21", "abc32", "abc13");
        Stream<String> strStream = list.stream().filter(element -> {
            System.out.println("filter() was called " + element);
            return element.contains("2");
        }).map(element -> {
            System.out.println("map() was called " + element);
            return element.toUpperCase();
        });

        System.out.println("Starting with terminal operation");
        List<String> lst = strStream.collect(Collectors.toList());
    }

    private static void createStreamWithRandom() {
        Random random = new Random();
        IntStream intStream = random.ints(10);

        intStream.forEach(item -> System.out.println(item));
    }

    /**
     * A mutable reduction operation accumulates input elements into a mutable result container,
     * such as a Collection or StringBuilder,
     * as it processes the elements in the stream.
     *
     */
    private static void createStreamWithMutableReduction() {

    }

    private static void doReductionOperation() {

    }
}
