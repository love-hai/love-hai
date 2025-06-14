package com.LoveSea.fengCore.downloader;

import com.LoveSea.fengCore.commons.utils.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author xiahaifeng
 */

public class WithProxyAuthDownloader {
    // 设置超时时间（单位：毫秒）
    private static final int connectTimeout = 10_000;  // 连接超时：10秒
    private static final int socketTimeout = 15_000;   // 读取数据超时：15秒
    private static final int requestTimeout = 10_000;  // 请求超时：10秒

    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(connectTimeout)
            .setSocketTimeout(socketTimeout)
            .setConnectionRequestTimeout(requestTimeout)
            .build();

    private static SSLContext sslContext;

    static {
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new RuntimeException("创建 SSL 上下文失败", e);
        }
    }

    // 配置 SSL 工厂，允许所有 HTTPS 证书（包括自签名）
    private static SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(
            sslContext,
            NoopHostnameVerifier.INSTANCE
    );

    public WithProxyAuthDownloader(WithProxyAuth withProxyAuth) {
        Objects.requireNonNull(withProxyAuth, "withProxyAuth 不能为空");
        this.withProxyAuth = withProxyAuth;
    }

    private final WithProxyAuth withProxyAuth;

    protected File withProxyAuthDownload(String urlStr, String targetDir, String suggestFileName) {
        ResourceUrlFileFullName ruFullName;
        if (null == suggestFileName || suggestFileName.trim().isEmpty()) {
            ruFullName = ResourceUrlFileFullName.parse(urlStr);
        } else {
            ruFullName = ResourceUrlFileFullName.parseFileFullName(suggestFileName);
        }
        String fileName = ruFullName.fileName();
        String fileType = ruFullName.fileExtension();
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(sslFactory);
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        if (withProxyAuth.hasProxy()) {
            HttpHost httpHost = new HttpHost(withProxyAuth.host(), withProxyAuth.port());
            httpClientBuilder.setProxy(httpHost);
        }
        if (withProxyAuth.hasAuth()) {
            AuthScope authScope = new AuthScope(withProxyAuth.host(), withProxyAuth.port());
            Credentials usernamePasswordCredentials = new UsernamePasswordCredentials(withProxyAuth.username(), withProxyAuth.password());
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(authScope, usernamePasswordCredentials);
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        }
        try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
            HttpEntity httpEntity = httpClient.execute(new HttpGet(urlStr)).getEntity();
            // 是否连接成功
            try (InputStream inputStream = httpEntity.getContent()) {
                if (null == fileType) {
                    String contentType = httpEntity.getContentType().getValue();
                    fileType = FileExtensionMIMEResource.getExtensionByContentType(contentType);
                }
                if (fileType.trim().isEmpty()) {
                    suggestFileName = fileName;
                } else {
                    suggestFileName = fileName + "." + fileType;
                }
                String tempFileName = TempFileName.getTempFileName();
                File tempFile = new File(targetDir, tempFileName);
                try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                File newFile = FileUtils.getUnDuplicatedFile(targetDir, suggestFileName);
                if (!tempFile.renameTo(newFile)) {
                    throw new RuntimeException("重命名文件失败");
                }
                return newFile;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}