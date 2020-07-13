package com.muse47.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

/**
 * @Description 将字符写入到buffer中，从buffer通过文件输出流保存到文件
 * @Author Simula47
 * @Date 2020/5/10 23:08
 */
public class  NioTest3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] message = "Hello world welcome!".getBytes();

        for (int i = 0; i < message.length; i++) {
            byteBuffer.put(message[i]);
        }

        //翻转buffer，从读取到写入
        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }

/*    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        System.out.println("capacity: " + buffer.capacity());

        for (int i = 0; i < 5; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        System.out.println("before flip limit: " + buffer.limit());

        buffer.flip();

        System.out.println("after flip limit: " + buffer.limit());

        System.out.println("enter while loop");

        while (buffer.hasRemaining()) {
            System.out.println("position: " + buffer.position());
            System.out.println("limit: " + buffer.limit());
            System.out.println("capacity: " + buffer.capacity());

            System.out.println(buffer.get());
        }
    }*/
}
