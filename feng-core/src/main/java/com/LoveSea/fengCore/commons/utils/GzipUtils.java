package com.LoveSea.fengCore.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author xiahaifeng
 */
@Slf4j
public class GzipUtils {
    /**
     * 文本数据gzip压缩
     */
    public static String gzipCompress(String text) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(text)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
            gzipOutputStream.flush();
            gzipOutputStream.finish();
        } catch (Exception e) {
            log.error("gzipCompress error", e);
            return null;
        }
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    /**
     * 文本数据gzip解压
     */
    public static String gzipDecompress(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(text));
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
            byte[] buffer = new byte[256];
            int len;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("gzipDecompress error", e);
            return null;
        }
        return byteArrayOutputStream.toString();
    }
}