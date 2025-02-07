package com.LoveSea.fengCore.commons.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author xiahaifeng
 */

public class UrlUtils {


    /**
     * downloadUrlTransfer : 下载地址转码<br>
     *
     * @param originalUrl 原始下载地址
     * @return 转码后的下载地址
     */
    public static String downloadUrlTransfer(String originalUrl) {
        int qMask = originalUrl.indexOf("?");
        String baseUrl;
        String params;
        if (qMask == -1) {
            baseUrl = originalUrl;
            params = "";
        } else {
            baseUrl = originalUrl.substring(0, qMask);
            params = originalUrl.substring(qMask);
        }
        int lastSlashes = baseUrl.lastIndexOf("/");
        // 获取域名 例如：http://spr45rkqa.hd-bkt.clouddn.com/xxx/
        String domain = baseUrl.substring(0, lastSlashes + 1);
        // 获取文件名
        String fileName = baseUrl.substring(lastSlashes + 1);
        // 将文件名进行转码
        StringBuilder fileNameEncode = new StringBuilder();
        for (int i = 0; i < fileName.length(); i++) {
            if (fileName.charAt(i) == ' ') {
                fileNameEncode.append("%20");
                continue;
            }
            if (fileName.charAt(i) > 128) {
                String encode = URLEncoder.encode(String.valueOf(fileName.charAt(i)), StandardCharsets.UTF_8);
                fileNameEncode.append(encode);
                continue;
            }
            fileNameEncode.append(fileName.charAt(i));
        }
        fileName = fileNameEncode.toString();
        // 空格会被转码为+，需要将+转换为%20
        fileName = fileName.replaceAll("\\+", "%20");
        return domain + fileName + params;
    }

}