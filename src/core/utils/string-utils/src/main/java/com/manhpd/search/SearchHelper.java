package com.manhpd.search;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchHelper {

    private static Set<String> NON_WORDS = new HashSet<>() {{
        add("the"); add("and"); add("of"); add("to"); add("a");
        add("i"); add("it"); add("in"); add("or"); add("is");
        add("d"); add("s"); add("as"); add("so"); add("but");
        add("be");
    }};

    public static Map wordFreqTraditional(String words) {
        long startTime = System.currentTimeMillis();
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        Matcher m = Pattern.compile("\\w+").matcher(words);
        while (m.find()) {
            String word = m.group().toLowerCase();
            if (!NON_WORDS.contains(word)) {
                if (wordMap.get(word) == null) {
                    wordMap.put(word, 1);
                } else {
                    wordMap.put(word, wordMap.get(word) + 1);
                }
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Time process of wordFreqTraditional() method is: " + duration);

        return wordMap;
    }

    public static Map wordFreqStreamJava8(String words) {
        long startTime = System.currentTimeMillis();
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        regexToList(words, "\\w+").stream()
                .map(w -> w.toLowerCase())
        .filter(w -> !NON_WORDS.contains(w))
        .forEach(w -> wordMap.put(w, wordMap.getOrDefault(w, 0) + 1));

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Time process of wordFreqStreamJava8() method is: " + duration);

        return wordMap;
    }

    private static List<String> regexToList(String words, String regex) {
        List<String> wordList = new ArrayList<>();
        Matcher m = Pattern.compile(regex).matcher(words);

        while (m.find()) {
            wordList.add(m.group());
        }

        return wordList;
    }

}
