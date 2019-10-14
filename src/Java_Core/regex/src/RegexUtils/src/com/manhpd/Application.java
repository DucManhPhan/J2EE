package com.manhpd;

import com.manhpd.number.RegexNumberUtils;
import com.manhpd.string.RegexStringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public static void main(String[] args) {
        String str = "before <b>first before</b> before <b>before before</b>";
        String result = RegexStringUtils.replaceAllMatchesByOtherMatches(str);
        System.out.println(result);
    }

}
