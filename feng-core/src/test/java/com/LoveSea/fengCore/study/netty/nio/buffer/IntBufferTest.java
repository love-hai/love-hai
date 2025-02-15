package com.LoveSea.fengCore.study.netty.nio.buffer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.IntBuffer;

/**
 * @author xiahaifeng
 */
@Slf4j
public class IntBufferTest {

    @Test
    public void testPutAndRead() {
        IntBuffer intBuffer = IntBuffer.allocate(2);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            log.info("{}", intBuffer.get());
        }
    }

    @Test
    public void testBufferSlice() {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }
        intBuffer.position(3);
        intBuffer.limit(7);
        IntBuffer slice = intBuffer.slice();
        while (slice.hasRemaining()) {
            log.info("{}", slice.get());
        }
        slice.clear();
        for (int i = 0; i < slice.capacity(); i++) {
            slice.put(i);
        }
        intBuffer.position(0);
        intBuffer.limit(intBuffer.capacity());
        while (intBuffer.hasRemaining()) {
            log.info("{}", intBuffer.get());
        }
    }



}