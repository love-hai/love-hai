package com.LoveSea.fengCore.study.netty.nio.buffer;

import org.junit.jupiter.api.Test;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.Random;
import java.util.RandomAccess;

/**
 * @author xiahaifeng
 */

public class MappedBufferTest {

    @Test
    public void testMappedBuffer() throws Exception {
        String file = "C:\\Users\\xiaha\\Downloads\\test.txt";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            FileChannel fc = randomAccessFile.getChannel();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
            String s = "xiahaifeng,Hello World!";
            char[] sc = s.toCharArray();
            int i;
            for (i = 0; i < sc.length; i++) {
                mbb.put(i, (byte) sc[i]);
            }
            mbb.put(i++, (byte) '\n');
            while (i < 1024) {
                mbb.put(i, (byte) ' ');
                i++;
            }
        }
    }
}