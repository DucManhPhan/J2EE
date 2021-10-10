package com.manhpd.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Use old APIs before Java 8
 * Refer: https://javarevisited.blogspot.com/search/label/date%20and%20time%20tutorial?&max-results=3
 */
public class OldDateTimeUtils {

    private static String FORMAT_12_HOUR = "yyyy-MM-dd hh:mm:ss aa";

    private static String FORMAT_24_HOUR = "dd/MM/yyyy HH:mm:ss";

    /**
     * Convert 24 hour format to 12 hour format
     * 24 hour format: dd/MM/yyyy HH:mm:ss
     * 12 hour format: yyyy-MM-dd hh:mm:ss aa
     */
    public static String from24HourTo12Hour(String strFormat24Hour, DateFormat dfFormat24Hour) {
        String output = "";

        try {
            DateFormat outputformat = new SimpleDateFormat(FORMAT_12_HOUR);

            // Conversion of input String to date
            Date date= dfFormat24Hour.parse(strFormat24Hour);

            // old date format to new date format
            output = outputformat.format(date);
            System.out.println(output);
        }catch(ParseException ex){
            ex.printStackTrace();
        }

        return output;
    }

    /**
     * Convert 12 hour format to 24 hour format
     * 24 hour format: dd/MM/yyyy HH:mm:ss
     * 12 hour format: yyyy-MM-dd hh:mm:ss aa
     */
    public static String from12HourTo24Hour(String strFormat12Hour, DateFormat dfFormat12Hour) {
        String output = "";

        try {
            DateFormat outputformat = new SimpleDateFormat(FORMAT_24_HOUR);

            // Conversion of input String to date
            Date date= dfFormat12Hour.parse(strFormat12Hour);

            // old date format to new date format
            output = outputformat.format(date);
            System.out.println(output);
        }catch(ParseException ex){
            ex.printStackTrace();
        }

        return output;
    }

}
