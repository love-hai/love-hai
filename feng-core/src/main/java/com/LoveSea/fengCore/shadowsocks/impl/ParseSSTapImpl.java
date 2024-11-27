package com.LoveSea.fengCore.shadowsocks.impl;

import com.LoveSea.fengCore.commons.utils.UrlInfo;
import com.LoveSea.fengCore.shadowsocks.ParseSSTap;
import com.LoveSea.fengCore.shadowsocks.SSRUrlGroup;
import com.LoveSea.fengCore.shadowsocks.SSRUrlItem;
import com.LoveSea.fengCore.shadowsocks.SSRUrlParseRes;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiahaifeng
 */
@Slf4j
public class ParseSSTapImpl implements ParseSSTap {
    @Override
    public SSRUrlParseRes parseSSrSubLink(String ssrLink) throws Exception {
        // 通过链接下载 node.txt
        URL url = new URL(ssrLink);
        // 声明http连接
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            // 设置请求方法
            con.setRequestMethod("GET");
            // 设置超时时间
            con.setConnectTimeout(15000);
            int responseCode = con.getResponseCode();
            if (HttpURLConnection.HTTP_OK != responseCode) {
                log.info("GET 请求失败，响应码：" + responseCode);
                throw new Exception("GET 请求失败，响应码：" + responseCode);
            }
            // 获取输入流
            try (InputStream in = con.getInputStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(in);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                // 读取输入流
                while ((inputLine = bufferedReader.readLine()) != null) {
                    content.append(inputLine).append("\n");
                }
                // 分割ssr
                List<String> ssrs = ssrSplit(content.toString());
                List<SSRUrlItem> ssrUrlItems = new ArrayList<>();
                // 去除空字符串
                ssrs.removeIf(String::isEmpty);
                // 解析ssr成SSRUrlItem
                ssrs.forEach(v -> Optional.ofNullable(parseSSr(v)).ifPresent(ssrUrlItems::add));
                SSRUrlParseRes ssrUrlParseRes = new SSRUrlParseRes();
                ssrUrlParseRes.setSSRUrlGroups(new ArrayList<>());
                List<SSRUrlGroup> ssrUrlGroups = ssrUrlParseRes.getSSRUrlGroups();
                // 分组
                ssrUrlItems.stream().collect(Collectors.groupingBy(SSRUrlItem::getGroup)).forEach((k, v) -> {
                    SSRUrlGroup ssrUrlGroup = new SSRUrlGroup();
                    ssrUrlGroup.setName(k);
                    ssrUrlGroup.setSSRUrlItems(v);
                    ssrUrlGroups.add(ssrUrlGroup);
                });
                return ssrUrlParseRes;
            }
        } finally {
            con.disconnect();
        }

    }

    @Override
    public SSRUrlItem parseSSr(String ssrUrl) {
        // 去除前缀 'ssr://'
        if (ssrUrl.startsWith("ssr://")) {
            ssrUrl = ssrUrl.substring(6);
        }
        int length = ssrUrl.length();
        if (length % 4 != 0) {
            ssrUrl += "=".repeat(4 - (length % 4));
        }
        SSRUrlItem ssrUrlItem = new SSRUrlItem();
        try {
            ssrUrl = URLDecoder.decode(ssrUrl, StandardCharsets.UTF_8);
            // 使用URL安全的Base64解码
            byte[] decode = Base64.getUrlDecoder().decode(ssrUrl);
            String decodedUrl = new String(decode, StandardCharsets.UTF_8);
            // 分割解码后的字符串
            String[] params = decodedUrl.split(":");
            // 确保解析到6个参数
            String server = params[0];  // 服务器地址
            ssrUrlItem.setServer(server);
            String port = params[1];    // 端口
            ssrUrlItem.setPort(Integer.valueOf(port));
            String protocol = params[2];  // 协议
            ssrUrlItem.setProtocol(protocol);
            String encodeMethod = params[3];  // 加密方式
            ssrUrlItem.setEncodeMethod(encodeMethod);
            String obfs = params[4];    // 混淆
            ssrUrlItem.setObfs(obfs);
            String other = params[5];  // 密码
            UrlInfo urlInfo = UrlInfo.parse(other);

            String encodePassword = urlInfo.getHost();
            if (null != encodePassword) {
                encodePassword = encodePassword.substring(0, encodePassword.length() - 1);
                String password = urlBase64Decode(encodePassword);
                ssrUrlItem.setPassword(password);
            }
            Map<String, String> urlParams = urlInfo.getParams();
            if (null != urlParams.get("obfsparam")) {
                String obfsparam = urlBase64Decode(urlParams.get("obfsparam"));
                ssrUrlItem.setObfsparam(obfsparam);
            }
            if (null != urlParams.get("protoparam")) {
                String protoparam = urlBase64Decode(urlParams.get("protoparam"));
                ssrUrlItem.setProtocolParam(protoparam);
            }
            if (null != urlParams.get("remarks")) {
                String remarks = urlBase64Decode(urlParams.get("remarks"));
                ssrUrlItem.setRemarks(remarks);
            }
            if (null != urlParams.get("group")) {
                String group = urlBase64Decode(urlParams.get("group"));
                ssrUrlItem.setGroup(group);
            }
            String ssrUrlStr = parserSSRUrlItem(ssrUrlItem);
            if (!ssrUrlStr.contains(ssrUrl)) {
                log.error("解析失败，解析前后不一致");
                return null;
            }
            return ssrUrlItem;
        } catch (IllegalArgumentException e) {
            log.error("Error during Base64 decoding: " + e.getMessage(), e);
            log.info(ssrUrl);
        }
        return null;
    }

    @Override
    public String parserSSRUrlItem(SSRUrlItem ssrUrlItem) {
        // v2-mixing.huaheyu.com:2033:auth_aes128_md5:aes-256-cfb:http_simple:SFVBSEU/
        // ?obfsparam=ZG93bmxvYWQubWljcm9zb2Z0LmNvbQ&protoparam=MTA2OTg1OjlpR0ZGZA&remarks=MSDop4bpopEv5LiL6L295LiT55So57q_6Lev&group=6Iqx56a-
        StringBuilder ssrUrl = new StringBuilder();
        ssrUrl.append(ssrUrlItem.getServer()).append(":");
        ssrUrl.append(ssrUrlItem.getPort()).append(":");
        ssrUrl.append(ssrUrlItem.getProtocol()).append(":");
        ssrUrl.append(ssrUrlItem.getEncodeMethod()).append(":");
        ssrUrl.append(ssrUrlItem.getObfs()).append(":");
        String password = ssrUrlItem.getPassword();
        String encodePassword = Base64.getUrlEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        ssrUrl.append(encodePassword).append("/?");
        if (null != ssrUrlItem.getObfsparam()) {
            String obfsparam = urlBase64Encode(ssrUrlItem.getObfsparam());
            ssrUrl.append("obfsparam=").append(obfsparam).append("&");
        }
        if (null != ssrUrlItem.getProtocolParam()) {
            String protoparam = urlBase64Encode(ssrUrlItem.getProtocolParam());
            ssrUrl.append("protoparam=").append(protoparam).append("&");
        }
        if (null != ssrUrlItem.getRemarks()) {
            String remarks = urlBase64Encode(ssrUrlItem.getRemarks());
            ssrUrl.append("remarks=").append(remarks).append("&");
        }
        if (null != ssrUrlItem.getGroup()) {
            String group = urlBase64Encode(ssrUrlItem.getGroup());
            ssrUrl.append("group=").append(group);
        }
        if (ssrUrl.charAt(ssrUrl.length() - 1) == '&') {
            ssrUrl.deleteCharAt(ssrUrl.length() - 1);
        }
        String ssrUrlStr = ssrUrl.toString();
        return "ssr://" + urlBase64Encode(ssrUrlStr);
    }


    private String urlBase64Decode(String str) {
        int length = str.length();
        if (length % 4 != 0) {
            str += "=".repeat(4 - (length % 4));
        }
        byte[] decode = Base64.getUrlDecoder().decode(str);
        return new String(decode, StandardCharsets.UTF_8);
    }

    private String urlBase64Encode(String str) {
        String encode = Base64.getUrlEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        while (encode.endsWith("=")) {
            encode = encode.substring(0, encode.length() - 1);
        }
        return encode;
    }

    private List<String> ssrSplit(String str) {
        // 对node 进行 base64 解码
        byte[] decode = Base64.getMimeDecoder().decode(str);
        String node = new String(decode, StandardCharsets.UTF_8);
        // 对node 进行分割
        String[] ssrUrls = node.split("\n");
        List<String> list = Lists.newArrayList(ssrUrls);
        list.removeIf(String::isEmpty);
        return list;
    }
}