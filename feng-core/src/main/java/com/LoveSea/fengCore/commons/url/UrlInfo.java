package com.LoveSea.fengCore.commons.url;

import com.LoveSea.fengCore.commons.utils.StringUtils;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

@Getter
public class UrlInfo {
    String url;
    String host;
    Map<String, String> params;

    public static UrlInfo parse(String url) {
        UrlInfo urlInfo = new UrlInfo();
        if (StringUtils.isBlank(url)) {
            return urlInfo;
        }
        urlInfo.url = url;
        String[] urlParts = url.split("\\?");
        urlInfo.host = urlParts[0];
        if (urlParts.length > 1) {
            urlInfo.params = getUrlParams(urlParts[1]);
        }
        return urlInfo;
    }

    private final static Map<String, String> EmptyMap = Map.of();

    private static Map<String, String> getUrlParams(String paramStr) {
        if (StringUtils.isBlank(paramStr)) {
            return EmptyMap;
        }
        Map<String, String> map = new HashMap<>();
        String[] params = paramStr.split("&");
        for (String p : params) {
            String[] kv = p.split("=");
            if (kv.length == 2) {
                map.put(kv[0], kv[1]);
            }
        }
        return map;
    }
}