package com.manhpd.encoding;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class EncodingHelperTest {

    @Test
    public void ISO8859_1ToUtf16Test() throws UnsupportedEncodingException {
        String string = "Ð\\x9cÐ¾Ñ\\x81ÐºÐ²Ð°";
//        System.out.println(EncodingHelper.toUtf8(string));

        EncodingHelper.ISO8859_1ToUtf16(string);
    }

}
