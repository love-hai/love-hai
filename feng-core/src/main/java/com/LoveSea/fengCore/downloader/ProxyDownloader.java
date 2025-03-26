package com.LoveSea.fengCore.downloader;


import com.LoveSea.fengCore.build.LongParamsBuilder;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author xiahaifeng
 */

public class ProxyDownloader extends WithProxyAuthDownloader implements Downloader {
    protected ProxyDownloader(ProxyDownloaderBuilder builder) {
        super(builder.toWithProxyAuth());
    }

    @Override
    public File downloadFile(String url, String targetDir, String suggestFileName) {
        ResourceURLTypeEnum resourceUrlTypeEnum = ResourceURLTypeEnum.fromString(url);
        return switch (resourceUrlTypeEnum) {
            case DataUri -> DataUri.parseDataUri(url)
                    .toFileTransform()
                    .transform(targetDir, suggestFileName);
            case Http, Https -> proxyDownload(url, targetDir, suggestFileName);
        };
    }

    @Override
    public File downloadFile(String url, String targetDir) {
        ResourceURLTypeEnum resourceUrlTypeEnum = ResourceURLTypeEnum.fromString(url);
        return switch (resourceUrlTypeEnum) {
            case DataUri -> DataUri.parseDataUri(url)
                    .toFileTransform()
                    .transform(targetDir);
            case Http, Https -> proxyDownload(url, targetDir, null);
        };
    }


    private File proxyDownload(String urlStr, String targetDir, String suggestFileName) {
        return super.withProxyAuthDownload(urlStr, targetDir, suggestFileName);
    }

    public static class ProxyDownloaderBuilder implements LongParamsBuilder<ProxyDownloader, ProxyDownloaderBuilder> {
        private String host;
        private Integer port;
        private String username;
        private String password;
        private boolean hasAuth = false;
        private boolean hasProxy = false;

        public ProxyDownloaderBuilder host(String host, int port) {
            Pattern pattern = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");
            if (!pattern.matcher(host).matches()) {
                throw new IllegalArgumentException("host 格式不正确");
            }
            if (port <= 0 || port > 65535) {
                throw new IllegalArgumentException("port 范围不正确");
            }
            this.host = host;
            this.port = port;
            this.hasProxy = true;
            return this;
        }

        public ProxyDownloaderBuilder auth(String username, String password) {
            Objects.requireNonNull(username, "username 不能为空");
            Objects.requireNonNull(password, "password 不能为空");
            this.username = username;
            this.password = password;
            this.hasAuth = true;
            return this;
        }

        public WithProxyAuth toWithProxyAuth() {
            return new WithProxyAuth(hasAuth, hasProxy, host, port, username, password);
        }

        @Override
        public ProxyDownloader build() {
            return new ProxyDownloader(this);
        }

        @Override
        public ProxyDownloaderBuilder self() {
            return this;
        }
    }
}