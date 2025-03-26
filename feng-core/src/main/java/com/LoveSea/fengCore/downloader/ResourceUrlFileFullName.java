package com.LoveSea.fengCore.downloader;

/**
 * @author xiahaifeng
 */

public record ResourceUrlFileFullName(String fileFullName, String fileName, String fileExtension) {
    public static ResourceUrlFileFullName parse(String resourceUrl) {
        int index;
        String noParamsUrl = (index = resourceUrl.indexOf("?")) > 0 ? resourceUrl.substring(0, index) : resourceUrl;
        String suggestFileName = noParamsUrl.substring(noParamsUrl.lastIndexOf("/") + 1);
        // 此时suggestFileName中可能包含 %20 等特殊字符
        suggestFileName = new HttpSpecialChar().decodeURLChinese(suggestFileName);
        String fileName;
        String fileType;
        index = suggestFileName.lastIndexOf(".");
        if (index == -1) {
            fileName = suggestFileName;
            fileType = null;
        } else {
            fileName = suggestFileName.substring(0, index);
            fileType = suggestFileName.substring(index);
        }
        return new ResourceUrlFileFullName(suggestFileName, fileName, fileType);
    }

    public static ResourceUrlFileFullName parseFileFullName(String suggestFileName) {
        String fileName;
        String fileType;
        int index = suggestFileName.lastIndexOf(".");
        if (index == -1) {
            fileName = suggestFileName;
            fileType = null;
        } else {
            fileName = suggestFileName.substring(0, index);
            fileType = suggestFileName.substring(index);
        }
        return new ResourceUrlFileFullName(suggestFileName, fileName, fileType);
    }

}