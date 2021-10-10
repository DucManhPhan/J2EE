package com.manhpd;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessDataService {

    private static IO<List<String>> readFile(String file) {
        return () -> {
            try (Stream<String> stream = Files.lines(Paths.get(file))) {
                return stream.filter(line -> line.startsWith("SD"))
                             .collect(Collectors.toList());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private static List<String> log(List<String> data) {
        System.out.println("Data with discount: " + data.size());
        return data;
    }

    private static IO<Unit> writeFile(List<String> discountedSales, String file) {
        return () -> {
            try {
                Files.write(Paths.get(file), discountedSales, Charset.defaultCharset());
                System.out.println("Written to file: " + file);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            return Unit.instance;
        };
    }

    public static IO<Unit> process(String inputFile, String outputFile) {
        IO<Unit> program = readFile(inputFile)
                           .map(data -> log(data))
                           .flatMap(data -> writeFile(data, outputFile)) ;
        return program;
    }

}
