package com.LoveSea.fengCore.commons.utils;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class UrlUtil {
    /**
     * getUrlParams : 获取url参数
     */
    @NonNull
    public static Map<String, String> getUrlParams(String url) {
        if (MyStringUtils.isBlank(url)) {
            return new HashMap<>();
        }
        Map<String, String> map = new HashMap<>();
        int endIndex = url.indexOf("?");
        endIndex = -1 == endIndex ? url.length() : endIndex;
        String mainUrl = url.substring(0, endIndex);
        map.put("server", mainUrl);
        String param = url.substring(url.indexOf("?") + 1);
        if (MyStringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (String p : params) {
            String[] kv = p.split("=");
            if (kv.length == 2) {
                map.put(kv[0], kv[1]);
            }
        }
        return map;
    }
}