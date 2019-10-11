package com.manhpd;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    /**
     * Count occurrence of character in string by using Regex
     *
     * @param str
     * @param c
     * @return
     */
    public static int count(String str, char c) {
        Matcher matcher = Pattern.compile(String.valueOf(c))
                .matcher(str);
        int res = 0;
        while (matcher.find()) {
            ++res;
        }

        return res;
    }

    /**
     * Count occurrence of character in string by using stream
     *
     * @param str
     * @param c
     * @return
     */
    public static long countByStream(String str, char c) {
        return str.chars()
                .filter(item -> c == item)
                .count();
    }

    /**
     * Remove all whitespace in string
     *
     * @param str
     * @return
     */
    public static String removeWhitespaces(String str) {
        String regex = "[\\s]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("");
    }

    /**
     * Check whether a string contains only alphabets or not
     *
     * @param str
     * @return
     */
    public static boolean hasAlphabets(String str) {
        // use classes in java.util.regex
        String regex = "^[a-zA-Z]*$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(str);
//
//        return matcher.matches();

        // use matches() method of string
        return ((!str.equals(""))
                && (str != null)
                && (str.matches(regex)));
    }

    /**
     * Extract words from a string
     *
     * @param str
     * @return
     */
    public static List<String> extractWords(String str) {
        String regex = "(\\b[a-zA-Z]*\\b)[^\\w]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            String tmp = matcher.group();
            System.out.println(matcher.group());
        }

        return Collections.emptyList();
    }

}
