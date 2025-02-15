package com.loveSea.nettyShow.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * @author xiahaifeng
 */

public class ByteBufferExample {

    public static void main(String[] args) {
        final byte[] bytes = new byte[1024];
        int loop = 180;
        long startTime = System.currentTimeMillis();
        ByteBuf poolBuffer;
        for (int i = 0; i < loop; i++) {
            poolBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            poolBuffer.writeBytes(bytes);
            poolBuffer.release();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("poolBuffer:"+(endTime -startTime)+"ms");

        startTime = System.currentTimeMillis();
        ByteBuf buffer;
        for (int i = 0; i < loop; i++) {
            buffer = Unpooled.directBuffer(1024);
            buffer.writeBytes(bytes);
            buffer.release();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Unpooled:"+ (endTime - startTime) + "ms");
    }


}