package com.muse47.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @Description 取出Buffer中的整形
 * @Author Simula47
 * @Date 2020/5/10 22:15
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
