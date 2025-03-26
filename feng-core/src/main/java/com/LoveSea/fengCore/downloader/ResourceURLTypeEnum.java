package com.LoveSea.fengCore.downloader;

public enum ResourceURLTypeEnum {
    DataUri,
    Http,
    Https,
    ;

    public static ResourceURLTypeEnum fromString(String url) {
        if (url.startsWith("data:")) {
            return DataUri;
        } else if (url.startsWith("http:")) {
            return Http;
        } else if (url.startsWith("https:")) {
            return Https;
        } else {
            throw new IllegalArgumentException("Unsupported URL type: " + url);
        }
    }
}
