package com.muse47.nio;

import java.nio.ByteBuffer;

/**
 * @Description ByteBuffer类型化的put与get方法
 * @Author Simula47
 * @Date 2020/5/14 15:35
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(5000000L);
        buffer.putDouble(14.123456);
        buffer.putChar('你');
        buffer.putShort((short) 2);
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
