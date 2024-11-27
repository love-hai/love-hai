package com.LoveSea.fengCore.shadowsocks.impl;

import com.LoveSea.fengCore.shadowsocks.ClashToSSr;
import com.LoveSea.fengCore.shadowsocks.ParseSSTap;
import com.LoveSea.fengCore.shadowsocks.SSRUrlItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiahaifeng
 */
@Slf4j
public class ClashToSSrImpl implements ClashToSSr {

    private String group = "clash";

    Yaml yaml;
    ParseSSTap parseSSTap;

    public ClashToSSrImpl() {
        this.parseSSTap = new ParseSSTapImpl();
        this.yaml = new Yaml();
    }

    public ClashToSSrImpl(String group) {
        this();
        this.group = group;
    }

    @Override
    public String transform(File file) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            return transform(in);
        }
    }

    @Override
    public String transform(String configUrl) throws IOException {
        // 构造URL
        URL url = new URL(configUrl);
        // 声明http连接
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // 设置请求方法
        con.setRequestMethod("GET");
        // 设置超时时间
        con.setConnectTimeout(15000);
        try (InputStream in = con.getInputStream()) {
            return transform(in);
        } finally {
            con.disconnect();
        }
    }

    @Override
    public String transform(InputStream in) {
        Map<String, Object> clashConfig = yaml.load(in);
        if (Objects.isNull(clashConfig)) {
            log.error("clash配置文件解析失败");
            return "";
        }
        List<LinkedHashMap<String, Object>> proxies = (List) clashConfig.get("proxies");
        StringBuilder ssrUrls = new StringBuilder();
        for (LinkedHashMap<String, Object> proxy : proxies) {
            String type = (String) proxy.get("type");
            if (StringUtils.isBlank(type)) {
                continue;
            }
            String ssrUrl = switch (type) {
                case "ss" -> ssTypeHandler(proxy);
                case "ssr" -> ssrTypeHandler(proxy);
                default -> null;
            };
            if (StringUtils.isNotBlank(ssrUrl)) {
                ssrUrls.append(ssrUrl).append("\n");
            }
        }


        return ssrUrls.toString();
    }

    private String ssTypeHandler(LinkedHashMap<String, Object> proxy) {
        String name = (String) proxy.get("name");
        String server = (String) proxy.get("server");
        Integer port = (Integer) proxy.get("port");
        String cipher = (String) proxy.get("cipher");
        String password = (String) proxy.get("password");
        Boolean udp = (Boolean) proxy.get("udp");
        SSRUrlItem ssrUrlItem = new SSRUrlItem();
        ssrUrlItem.setGroup(group);
        ssrUrlItem.setRemarks(name);
        ssrUrlItem.setServer(server);
        ssrUrlItem.setPort(port);
        ssrUrlItem.setPassword(password);
        ssrUrlItem.setEncodeMethod(cipher);
        return parseSSTap.parserSSRUrlItem(ssrUrlItem);
    }

    private String ssrTypeHandler(LinkedHashMap<String, Object> proxy) {
        SSRUrlItem ssrUrlItem = new SSRUrlItem();
        ssrUrlItem.setRemarks((String) proxy.get("name"));
        ssrUrlItem.setServer((String) proxy.get("server"));
        ssrUrlItem.setPort((Integer) proxy.get("port"));
        ssrUrlItem.setEncodeMethod((String) proxy.get("cipher"));
        ssrUrlItem.setPassword((String) proxy.get("password"));
        ssrUrlItem.setProtocol((String) proxy.get("protocol"));
        ssrUrlItem.setProtocolParam((String) proxy.get("protocolparam"));
        ssrUrlItem.setObfs((String) proxy.get("obfs"));
        ssrUrlItem.setObfsparam((String) proxy.get("obfsparam"));
        ssrUrlItem.setGroup(group);
        return parseSSTap.parserSSRUrlItem(ssrUrlItem);
    }

    public static void main(String[] args) throws IOException {
        ClashToSSrImpl clashToSSrImpl = new ClashToSSrImpl();
        String yamlPath = "https://huahe.link/link/fuQAeyIRcEHjZ3zG?clash=2";
        String ss = clashToSSrImpl.transform(yamlPath);
        log.info(ss);
    }
}