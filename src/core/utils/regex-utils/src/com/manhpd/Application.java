package com.manhpd;

import com.manhpd.number.RegexNumberUtils;
import com.manhpd.string.RegexStringUtils;
import com.manhpd.validation.RegexValidationUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public static void main(String[] args) {
        String str = "12/10/2019";
        boolean result = RegexValidationUtils.validateDateFormat(str);
        System.out.println(result);
    }

}
