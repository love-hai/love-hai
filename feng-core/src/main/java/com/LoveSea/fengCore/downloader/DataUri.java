package com.LoveSea.fengCore.downloader;

import lombok.Getter;

/**
 * @author xiahaifeng
 */
@Getter
public class DataUri {
    private DataUri(String majorType, String subType, String encoding, String data) {
        this.majorType = majorType;
        this.subType = subType;
        this.encoding = encoding;
        this.data = data;
        this.type = majorType + "/" + subType;
    }

    public static boolean isDataUri(String dataUri) {
        return dataUri.startsWith("data:");
    }

    public static DataUri parseDataUri(String dataUri) {
        if (!dataUri.startsWith("data:")) {
            throw new IllegalArgumentException("无效的 data URI");
        }
        String[] parts = dataUri.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("无效的 data URI ,使用逗号分隔成" + parts.length);
        }
        String[] typeParts = parts[0].substring(5).split(";");
        if (typeParts.length < 2) {
            throw new IllegalArgumentException("无效的 data URI,类型部分为空");
        }
        String[] type = typeParts[0].split("/");
        if (type.length != 2) {
            throw new IllegalArgumentException("无效的 data URI,类型部分不是两部分");
        }
        String majorType = type[0];
        String subType = type[1];
        String encoding = typeParts[1];
        String data = parts[1];
        return new DataUri(majorType, subType, encoding, data);
    }

    public DataUriTransformFile toFileTransform() {
        return new DataUriTransformFile(this);
    }

    private final String type;
    private final String majorType;
    private final String subType;
    private final String encoding;
    private final String data;
}