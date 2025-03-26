package com.LoveSea.fengCore.downloader;

import java.util.UUID;

/**
 * @author xiahaifeng
 */

public class TempFileName {
    public static String getTempFileName() {
        // 临时文件名 UUID 等下载完成后再重命名
        return UUID.randomUUID().toString();
    }
}