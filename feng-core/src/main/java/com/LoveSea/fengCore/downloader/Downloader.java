package com.LoveSea.fengCore.downloader;

import java.io.File;

/**
 * @author xiahaifeng
 */

public interface Downloader {
    File downloadFile(String url, String targetDir, String suggestFileName);
    File downloadFile(String url, String targetDir);
}