package com.LoveSea.fengCore.study.netty.nio.channel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author xiahaifeng
 */
@Slf4j
public class FileChannelTest {

    @Test
    public void testFileRead() throws Exception {
        File file = new File("C:\\Users\\xiaha\\Downloads\\test.txt");
        try (FileInputStream inputStream = new FileInputStream(file);
             FileChannel fileChannel = inputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            outPutBuffer("初始化", byteBuffer);
            int read = fileChannel.read(byteBuffer);
            outPutBuffer("read", byteBuffer);
            byteBuffer.flip();
            outPutBuffer("flip", byteBuffer);
            while (byteBuffer.hasRemaining()) {
                log.info("{}", (char) byteBuffer.get());
            }
            outPutBuffer("get", byteBuffer);
            byteBuffer.clear();
            outPutBuffer("clear", byteBuffer);
        }


    }

    @Test
    public void testFileReadAll() throws Exception {
        File file = new File("C:\\Users\\xiaha\\Downloads\\test.txt");
        long start = System.currentTimeMillis();
        try (FileInputStream inputStream = new FileInputStream(file);
             FileChannel fileChannel = inputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            StringBuilder str = new StringBuilder();
            Charset charset = StandardCharsets.UTF_8;
            while ((fileChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    str.append(charset.decode(byteBuffer));
                }
                byteBuffer.clear();
            }
            log.info("{}", str);
        }
        log.info("耗时:{}", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[10];
            StringBuilder str = new StringBuilder();
            while (inputStream.read(bytes) != -1) {
                str.append(new String(bytes, StandardCharsets.UTF_8));
            }
            log.info("{}", str);
        }
        log.info("耗时:{}", System.currentTimeMillis() - start);
    }

    private void outPutBuffer(String prefix, Buffer buffer) {
        log.info(prefix);
        log.info("capacity:{}", buffer.capacity());
        log.info("position:{}", buffer.position());
        log.info("limit:{}", buffer.limit());
        log.info("\n");
    }


    @Test
    public void testFileOutPut() throws Exception {
        File file = new File("C:\\Users\\xiaha\\Downloads\\test.txt");
        try (FileOutputStream outputStream = new FileOutputStream(file);
             FileChannel fileChannel = outputStream.getChannel()) {
            String str = "Hello World";
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            char[] chars = str.toCharArray();
            int i = 0;
            while (i < chars.length) {
                byteBuffer.put((byte) chars[i]);
                i++;
                if (!byteBuffer.hasRemaining()) {
                    byteBuffer.flip();
                    fileChannel.write(byteBuffer);
                    byteBuffer.clear();
                }
            }
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        }
    }


}