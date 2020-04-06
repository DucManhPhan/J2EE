package com.manhpd.common;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UsingCharsets {

    public static void main(String[] args) throws IOException {
        Charset latin1 = StandardCharsets.ISO_8859_1;
        Charset utf8 = StandardCharsets.UTF_8;

        String hello = "Hello world from Việt Nam đấy!!!";
        System.out.println("Length = " + hello.length());

//        writeFile(hello, utf8, "utf8");
//        writeFile(hello, latin1, "latin1");

//        readFile(utf8, "utf8");
        readFile(latin1, "latin1");
    }

    public static void writeFile(String content, Charset charset, String newFileName) throws IOException {
        CharBuffer charBuffer = CharBuffer.allocate(1024 * 1024);
        charBuffer.put(content);
        charBuffer.flip();

        // In order to write the content to a file, we need to use Channel.
        // But Channel only uses ByteBuffer to deal with it.
        // So, we need to convert CharBuffer to ByteBuffer.
        ByteBuffer byteBuffer = charset.encode(charBuffer);
        Path path = Paths.get("files/hello-" + newFileName + ".txt");
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            fileChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Size of a new file = " + Files.size(path));
    }

    public static void readFile(Charset charset, String newFileName) {
        Path path = Paths.get("files/hello-" + newFileName + ".txt");
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);

        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            fileChannel.read(byteBuffer);
        } catch (IOException ex) {
            // nothing to do
        }

        byteBuffer.flip();
        CharBuffer charBuffer = charset.decode(byteBuffer);
        String result = new String(charBuffer.array());
        System.out.println("Result = " + result);
    }

}
