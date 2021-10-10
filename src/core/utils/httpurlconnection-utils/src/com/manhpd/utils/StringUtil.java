package com.manhpd.utils;

public class StringUtil {

    public static String getFileNameFromDispositionHeader(String disposition) {
        int index = disposition.indexOf("filename=");
        return index > 0 ? disposition.substring(index + 10, disposition.length() - 1) : "";
    }

    public static String getFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.length());
    }

}
