package com.muse47.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Description
 * @Author Simula47
 * @Date 2020/7/13 15:05
 */
public class ByteBufTest0 {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.writeByte(i);
        }
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i));
        }
    }
}
