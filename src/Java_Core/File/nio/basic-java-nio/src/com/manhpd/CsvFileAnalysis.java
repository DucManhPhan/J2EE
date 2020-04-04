package com.manhpd;

import com.manhpd.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class CsvFileAnalysis {

    public static void main(String[] args) {
        String filePath = "files/person.csv";
        analysisCsvFile(filePath);
    }

    public static void analysisCsvFile(String filePath) {
        Path path = Path.of(filePath);

        try (Stream<String> lines = Files.lines(path)) {
            lines.filter(line -> !line.startsWith("#"))
                 .flatMap(CsvFileAnalysis::lineToPerson)
                 .forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }
    }

    private static Stream<Person> lineToPerson(String line) {
        try {
            String[] elements = line.split(";");
            String name = elements[0];
            int age = Integer.parseInt(elements[1]);
            String city = elements[2];

            Person p = new Person(name, age, city);
            return Stream.of(p);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return Stream.empty();
    }

}
