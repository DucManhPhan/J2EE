package com.manhpd.common;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ReadingWritingBuffers {

    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        buffer.putInt(10);
        buffer.putInt(20);
        buffer.putInt(30);

        System.out.println("Position: " + buffer.position());   // the position is the next available position in the buffer
        System.out.println("Limit: " + buffer.limit());

        // the flip operation will set limit of ByteBuffer to the current position we have, that is 12.
//        buffer.flip();
//        IntBuffer intBuffer = buffer.asIntBuffer();
//        System.out.println("\nPosition: " + intBuffer.position());   // the position is the next available position in the buffer
//        System.out.println("Limit: " + intBuffer.limit());

        // if i = 0, because when we convert ByteBuffer to IntBuffer, we started at the current position of this buffer.
        // So IntBuffer does not start at position 0 of this ByteBuffer but instead at position 12.
//        int i = intBuffer.get();
//        System.out.println("i = " + i);

        // write content to a file
        Path path = Paths.get("files/ints.txt");
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            buffer.flip();
            fileChannel.write(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("File = " + Files.size(path));

        // read content of a file
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            buffer.clear();
            fileChannel.read(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Position: " + buffer.position());
        System.out.println("Limit: " + buffer.limit());

        buffer.flip();
        IntBuffer intBuffer = buffer.asIntBuffer();
        List<Integer> ints = new ArrayList<>();

        try {
            while (true) {
                ints.add(intBuffer.get());
            }
        } catch (BufferUnderflowException ex) {
            // nothing to do
        }

        System.out.println("Size = " + ints.size());
        ints.forEach(System.out::println);
    }

}
