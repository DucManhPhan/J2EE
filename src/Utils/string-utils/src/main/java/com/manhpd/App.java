package com.manhpd;


import com.manhpd.encoding.EncodingHelper;

import javax.sound.sampled.AudioFormat;
import java.io.UnsupportedEncodingException;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String string = "Ð\\x9cÐ¾Ñ\\x81ÐºÐ²Ð°";
//        System.out.println(EncodingHelper.toUtf8(string));

        EncodingHelper.ISO8859_1ToUtf8(string);
    }
}
