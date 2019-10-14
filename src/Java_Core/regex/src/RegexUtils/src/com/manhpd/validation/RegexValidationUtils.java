package com.manhpd.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidationUtils {

    public static boolean validateEmail(String str) {
        String regex = "\\A\\S+@\\S+\\Z";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

    /**
     * Input: 123-456-7890 or 123.456.7890 or (123) 456 7890
     * Output: (123) 456-7890
     *
     * @param str
     * @return
     */
    public static String validateNorthAmericanPhoneNumbers(String str) {
        String regex = "\\A\\(?([0-9]{3})\\)?[-.\\s]+([0-9]{3})[-.\\s]+([0-9]{4})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        String switchValues = "\\($1\\) $2-$3";
        return matcher.replaceAll(switchValues);
    }

    /**
     * Validates dates in the traditional formats mm/dd/yy, mm/dd/yyyy,
     * dd/mm/yy, and dd.mm/yyyy
     *
     * @param str
     * @return
     */
    public static boolean validateDateFormat(String str) {
        String regex = "^(?:" +
                        // m/d or mm/dd
                        "(1[0-2]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])" +
                        "|" +
                        // d/m or dd/mm
                        "(3[01]|[12][0-9]|0?[1-9])/(1[0-2]|0?[1-9])" +
                        ")" +
                        // /yy or /yyyy
                        "(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

}
