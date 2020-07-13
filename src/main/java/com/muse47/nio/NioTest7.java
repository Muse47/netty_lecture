package com.muse47.nio;

import java.nio.ByteBuffer;

/**
 * @Description 只读Buffer，我们可以随时将一个普通Buffer调用asReadOnlyBuffer方法返回一个只读Buffer。但不能将一个只读buffer转换为读写buffer
 * @Author Simula47
 * @Date 2020/5/14 16:16
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        System.out.println(readOnlyBuffer.getClass());

        readOnlyBuffer.position(0);
    }
}
