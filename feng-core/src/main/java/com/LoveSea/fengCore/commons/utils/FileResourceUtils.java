package com.LoveSea.fengCore.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileResourceUtils {
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileResourceUtils(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public InputStream resourceInputStream(String resourcePath) throws IOException {
        return resourceLoader.getResource("classpath:" + resourcePath).getInputStream();
    }
    public byte[] resourceToByteArray(String resourcePath) throws IOException {
        // 使用 ByteArrayOutputStream 读取 InputStream 数据
        try (InputStream inputStream = this.resourceInputStream(resourcePath);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }
}
