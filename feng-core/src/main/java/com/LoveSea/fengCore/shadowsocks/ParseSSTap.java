package com.LoveSea.fengCore.shadowsocks;

import com.LoveSea.fengCore.commons.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiahaifeng
 */
@Slf4j
public class ParseSSTap {

    public static void main(String[] args) throws Exception {
        ParseSSTap parseSSTap = new ParseSSTap();
        SSRUrlParseRes ssrUrlParseRes = parseSSTap.parseSSrSubLink("https://huahe.link/link/fuQAeyIRcEHjZ3zG?sub=1&extend=1");
        log.info(ssrUrlParseRes.toString());
    }

    SSRUrlParseRes parseSSrSubLink(String ssrLink) throws Exception {
        // 通过链接下载 node.txt
        URL url = new URL(ssrLink);
        // 声明http连接
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // 设置请求方法
        con.setRequestMethod("GET");
        // 设置超时时间
        con.setConnectTimeout(15000);
        con.getInputStream();
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 获取输入流
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine).append("\n");
                }
                // 关闭输入流
                in.close();
                // 打印文件内容
                List<String> ssrUrls = ssrUrls(content.toString());
                List<SSRUrlItem> ssrUrlItems = new ArrayList<>();
                for (String ssrUrl : ssrUrls) {
                    SSRUrlItem ssrUrlItem = parseSSrUrl(ssrUrl);
                    if (null != ssrUrlItem) {
                        ssrUrlItems.add(ssrUrlItem);
                    }
                }
                SSRUrlParseRes ssrUrlParseRes = new SSRUrlParseRes();
                List<SSRUrlGroup> ssrUrlGroups = new ArrayList<>();
                Map<String, List<SSRUrlItem>> groupMap = ssrUrlItems.stream().collect(Collectors.groupingBy(SSRUrlItem::getGroup));
                for (Map.Entry<String, List<SSRUrlItem>> entry : groupMap.entrySet()) {
                    SSRUrlGroup ssrUrlGroup = new SSRUrlGroup();
                    ssrUrlGroup.setName(entry.getKey());
                    ssrUrlGroup.setSSRUrlItems(entry.getValue());
                    ssrUrlGroups.add(ssrUrlGroup);
                }
                ssrUrlParseRes.setSSRUrlGroups(ssrUrlGroups);
                return ssrUrlParseRes;
            }
        } else {
            log.info("GET 请求失败，响应码：" + responseCode);
        }
        return null;
    }

    SSRUrlItem parseSSrUrl(String ssrUrl) {
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

            Map<String, String> urlParams = UrlUtil.getUrlParams(other);
            String encodePassword = urlParams.get("server");
            if (null != encodePassword) {
                encodePassword = encodePassword.substring(0, encodePassword.length() - 1);
                String password = urlBase64Decode(encodePassword);
                ssrUrlItem.setPassword(password);
            }
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
            return ssrUrlItem;
        } catch (IllegalArgumentException e) {
            log.error("Error during Base64 decoding: " + e.getMessage(), e);
            log.info(ssrUrl);
        }
        return null;
    }


    String urlBase64Decode(String str) {
        int length = str.length();
        if (length % 4 != 0) {
            str += "=".repeat(4 - (length % 4));
        }
        byte[] decode = Base64.getUrlDecoder().decode(str);
        return new String(decode, StandardCharsets.UTF_8);
    }

    List<String> ssrUrls(String str) {
        // 对node 进行 base64 解码
        byte[] decode = Base64.getMimeDecoder().decode(str);
        String node = new String(decode, StandardCharsets.UTF_8);
        // 对node 进行分割
        String[] ssrUrls = node.split("\n");
        List<String> list = new ArrayList<>(List.of(ssrUrls));
        list.removeIf(String::isEmpty);
        return list;
    }
}