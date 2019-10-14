package com.manhpd.string;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexStringUtils {

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
        String regex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            String tmp = matcher.group();
            System.out.println(matcher.group());
        }

        return Collections.emptyList();
    }

    public static boolean isPunctuationCharacter(String str) {
        String regex = "\\$\\(\\)\\*\\+\\.\\?\\[\\\\\\^\\{\\|";
        return str.matches(regex);
    }

    /**
     * Finding all words ending with comma without including comma in the match
     * Ex:
     * Input: bat, cat, dog, fox
     * Output: bat - cat - dog
     *
     * @param str
     * @return
     */
    public static List<String> extractWordsWithoutComma(String str) {
        // use character classes and quantifiers
//        String regex = "[a-z]+,";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(str);
//        List<String> result = new ArrayList<>();
//
//        while (matcher.find()) {
//            String animal = matcher.group();
//            result.add(animal);
//        }
//
//        return result;

        // use lookahead
        String regex = "[a-z]+(?=,)";
        Matcher matcher = Pattern.compile(regex)
                                 .matcher(str);
        List<String> results = new ArrayList<>();

        while (matcher.find()) {
            String animal = matcher.group();
            results.add(animal);
        }

        return results;
    }

    /**
     * In an HTML file, find all numbers marked as bold.
     * If some bold text  contains multiple numbers, we want to match all of them separately.
     *
     * Input: 1 <b>2</b> 3 4 <b>5 6 7</b>
     * Output: 2, 5, 6, 7
     *
     * @param str
     * @return
     */
    public static List<String> extractDataInXml(String str) {
        // compatible with Java 4 and later
//        List<String> resultList = new ArrayList<>();
//        Pattern outerRegex = Pattern.compile("<b>(.*?)</b>", Pattern.DOTALL);
//        Pattern innerRegex = Pattern.compile("\\d+");
//        Matcher outerMatcher = outerRegex.matcher(str);
//
//        while (outerMatcher.find()) {
//            Matcher innerMatcher = innerRegex.matcher(outerMatcher.group());
//            while (innerMatcher.find()) {
//                resultList.add(innerMatcher.group());
//            }
//        }
//
//        return resultList;

        // compatible with Java 5 and later: efficient because innerMatcher is created only once
                List<String> resultList = new ArrayList<>();
        Pattern outerRegex = Pattern.compile("<b>(.*?)</b>", Pattern.DOTALL);
        Pattern innerRegex = Pattern.compile("\\d+");
        Matcher outerMatcher = outerRegex.matcher(str);
        Matcher innerMatcher = innerRegex.matcher(str);

        while (outerMatcher.find()) {
            innerMatcher.region(outerMatcher.start(), outerMatcher.end());
            while (innerMatcher.find()) {
                resultList.add(innerMatcher.group());
            }
        }

        return resultList;
    }

    /**
     * We have subject text as "str", we want to replace all before "text" in "str" to "after" string
     *
     * @param str
     * @param before
     * @param after
     */
    public static String replaceText(String str, String before, String after) {
        String result = "";

        try {
            Pattern pattern = Pattern.compile(before);
            Matcher matcher = pattern.matcher(str);

            try {
                result = matcher.replaceAll(after);
            } catch (IllegalArgumentException e) {
                // do something here
            } catch (IndexOutOfBoundsException e) {
                // do something here
            }
        } catch (PatternSyntaxException e) {
            // do something here
        }

        return result;
    }

    /**
     * We want to match pairs of words delimited by an equals sign, and swap
     * those words in the replacement.
     *
     * Input: Mary = James
     * Output: James = Mary
     *
     * @param str
     */
    public static String switchValuesInExpression(String str) {
        String detectedValues = "(\\w+)\\s*=\\s*(\\w+)";
        Pattern pattern = Pattern.compile(detectedValues);
        Matcher matcher = pattern.matcher(str);

        String switchValues = "$2 = $1";
        return matcher.replaceAll(switchValues);
    }

    /**
     * Replace all numbers in a string with the number multiplied by two.
     * Input: 1, 2, 3, 4, 5
     * Ouput: 2, 4, 6, 8, 10
     *
      * @param str
     * @return
     */
    public static String replaceTextWithExpression(String str) {
        StringBuffer result = new StringBuffer();
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            Integer doubleValue = Integer.parseInt(matcher.group()) * 2;
            matcher.appendReplacement(result, doubleValue.toString());
        }

        matcher.appendTail(result);
        return result.toString();
    }

    /**
     * In Html file, various passages are marked as bold with <b> tags.
     * Between each pair of bold tags, you want to replace all matches of the regular expression
     * ‹before› with the replacement text ‹after›.
     *
     * Input: before <b>first before</b> before <b>before before</b>
     * Ouput: before <b>first after</b> before <b>after after</b>
     *
     * @param str
     * @return
     */
    public static String replaceAllMatchesByOtherMatches(String str) {
        StringBuffer result = new StringBuffer();
        Pattern outerRegex = Pattern.compile("<b>.*?</b>");
        Pattern innerRegex = Pattern.compile("before");

        Matcher outerMatcher = outerRegex.matcher(str);

        while (outerMatcher.find()) {
            String afterText = innerRegex.matcher(outerMatcher.group()).replaceAll("after");
            outerMatcher.appendReplacement(result, afterText);
        }

        outerMatcher.appendTail(result);
        return result.toString();
    }

}
