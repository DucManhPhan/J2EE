package com.manhpd.encoding;


import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class EncodingHelper {

    /**
     * If you're dealing with character encodings other than UTF-16, you shouldn't be using java.lang.String or the char primitive.
     * You should only be using byte[] arrays or ByteBuffer objects.
     * Then, you can use java.nio.charset.Charset to convert between encodings:
     *
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String ISO8859_1ToUtf16(String content) throws UnsupportedEncodingException {
        // (1)
//        Charset utf8charset = Charset.forName("UTF-8");
//        Charset utf16becharset = Charset.forName("UTF-16BE");
//        Charset iso88591charset = Charset.forName("ISO-8859-1");
//
//        ByteBuffer inputBuffer = ByteBuffer.wrap(content.getBytes(iso88591charset));//new byte[]{(byte)0xC3, (byte)0xA2});
//
//        // decode UTF-8
//        CharBuffer data = utf16becharset.decode(inputBuffer);
//        System.out.println(data);
//
//        // encode ISO-8559-1
//        ByteBuffer outputBuffer = iso88591charset.encode(data);
//        byte[] outputData = outputBuffer.array();
//        System.out.println("Bytes of Western characters: " + outputData);

        // (2)
        byte[] bytes = content.getBytes("ISO-8859-1");
        System.out.println(new String(bytes, "UTF-16BE"));

        return new String(bytes, "UTF-16BE");
    }

}
