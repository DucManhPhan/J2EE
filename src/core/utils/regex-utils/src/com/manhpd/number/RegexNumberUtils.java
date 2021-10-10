package com.manhpd.number;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * - Escaping the metacharacters makes our regex easier to understand.
 *
 *
 */
public class RegexNumberUtils {

    public static List<String> extractDecimal(String str) {
        String regex = "[-+]?\\d*\\.\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        List<String> decimals = new ArrayList<>();
        while (matcher.find()) {
            String decimal = matcher.group();
            decimals.add(decimal);
        }

        return decimals;
    }

    /**
     * The hyphen (-) creates a range when it is placed between two characters. The range
     * consists of the character class with the character before the hyphen, the character after
     * the hyphen, and all characters that lie between them in numerical order.
     *
     * @param str
     * @return
     */
    public static boolean isHexadecimal(String str) {
        String regex = "[a-fA-F0-9]";   // "[a-fA-F\d]"
        return str.matches(regex);
    }

    /**
     * The caret (^) negates the character class if we place it immediately after the opening bracket.
     * A negated character class matches line break characters, unless you add them to the negated character class.
     *
     * @param str
     * @return
     */
    public static boolean isNotHexadecimal(String str) {
        String regex = "[^a-fA-F0-9]";
        return str.matches(regex);
    }



}
