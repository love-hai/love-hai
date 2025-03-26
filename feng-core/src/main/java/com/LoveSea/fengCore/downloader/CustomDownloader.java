package com.LoveSea.fengCore.downloader;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author xiahaifeng
 */
@Slf4j
public class CustomDownloader extends WithProxyAuthDownloader implements Downloader {

    private static final WithProxyAuth noProxyAuth = new WithProxyAuth(false, false, null, 0, null, null);

    private CustomDownloader() {
        super(noProxyAuth);
    }

    public static final CustomDownloader instance = new CustomDownloader();

    @Override
    public File downloadFile(String url, String targetDir, String suggestFileName) {
        ResourceURLTypeEnum resourceUrlTypeEnum = ResourceURLTypeEnum.fromString(url);
        return switch (resourceUrlTypeEnum) {
            case DataUri -> DataUri.parseDataUri(url)
                    .toFileTransform()
                    .transform(targetDir, suggestFileName);
            case Http, Https -> download(url, targetDir, suggestFileName);
        };
    }

    @Override
    public File downloadFile(String url, String targetDir) {
        ResourceURLTypeEnum resourceUrlTypeEnum = ResourceURLTypeEnum.fromString(url);
        return switch (resourceUrlTypeEnum) {
            case DataUri -> DataUri.parseDataUri(url)
                    .toFileTransform()
                    .transform(targetDir);
            case Http, Https -> download(url, targetDir, null);
        };
    }

    private File download(String urlStr, String targetDir, String suggestFileName) {
        return super.withProxyAuthDownload(urlStr, targetDir, suggestFileName);
    }
}