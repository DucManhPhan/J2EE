package com.manhpd;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static final String VIDEO_ID = "/watch\\?v=(.{11})";

    public static Set<String> extractVideoIds(String text) {
        Set<String> results = new HashSet<>();
        Pattern p = Pattern.compile(RegexUtils.VIDEO_ID);
        Matcher matcher = p.matcher(text);

        while (matcher.find()) {
            String data = matcher.group(1);
            results.add(data);
        }

        return results;
    }

    public static String extractVideoId(String text) {
        Pattern pattern = Pattern.compile(RegexUtils.VIDEO_ID);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return StringUtils.EMPTY;
    }

}
