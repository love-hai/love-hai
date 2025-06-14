package com.LoveSea.fengCore.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class FileUtils {

    /**
     * coverOldFile : 覆盖旧文件<br>
     *
     * @param urlString      url地址
     * @param exportFilePath 导出文件路径
     * @param fileName       文件名
     * @param isAppend       是否追加
     */
    public void coverOldFile(String urlString, String exportFilePath, String fileName, Boolean... isAppend) {
        File file = new File(exportFilePath + File.separator + fileName);
        try {
            if (file.exists()) {
                // 如果文件存在，删除文件
                file.delete();
            }
            this.downloadFile(urlString, exportFilePath, fileName, isAppend);
        } catch (Exception e) {
            log.error("删除文件失败", e);
        }
    }

    /**
     * downloadFile : 下载文件<br>
     *
     * @param urlString      url地址
     * @param exportFilePath 导出文件路径
     * @param fileName       文件名
     * @param isAppend       是否追加
     */
    public void downloadFile(String urlString, String exportFilePath, String fileName, Boolean... isAppend) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // 设置User-Agent头信息
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            String fullFileName;
            if (isAppend.length > 0 && isAppend[0]) {
                fullFileName = exportFilePath + File.separator + fileName;
            } else {
                fullFileName = this.getFileName(exportFilePath + File.separator + fileName);
            }
            try (InputStream is = con.getInputStream();
                 FileOutputStream os = new FileOutputStream(fullFileName, true)) {

                log.info("文件下载开始:" + fullFileName);
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len;
                // 输出的文件流
                // 开始读取
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
            } catch (IOException e) {
                log.error("文件下载异常", e);
            } finally {
                log.info("文件下载结束:" + fullFileName);
            }
        } catch (IOException e) {
            log.error("文件下载连接异常", e);
        }
    }

    /**
     * getFileName : 获取正确的文件名<br>
     * 如果文件已经存在，则在文件名后面加上序号
     *
     * @param path 文件路径
     */
    private String getFileName(String path) {
        if (StringUtils.isEmpty(path)) {
            throw new RuntimeException("文件路径不能为空");
        }
        int index = 0;
        //先将后缀和前面的部分分离出来
        String suffix = path.substring(path.lastIndexOf("."));
        String prefix = path.substring(0, path.lastIndexOf("."));
        String oldPrefix = prefix;
        while (true) {
            String fileName = prefix + suffix;
            File file = new File(fileName);
            if (file.exists()) {
                index++;
                prefix = oldPrefix + " (" + index + ")";
            } else {
                return fileName;
            }
        }
    }

    /**
     * dlAndUnpackZip : 下载并解压zip文件<br>
     *
     * @param urlPath  : url地址
     * @param filePath : 要下载的文件或者文件夹 存放的父文件夹路径
     */
    public void dlAndUnpackZip(String urlPath, String filePath) throws Exception {
        // 构造URL
        URL url = new URL(urlPath);
        // 声明http连接
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // 设置请求方法
        con.setRequestMethod("GET");
        // 设置超时时间
        con.setConnectTimeout(15000);
        // 根据建议的文件名，获取一个不重复的文件
        File destDirectory = new File(filePath);
        if (!destDirectory.exists())
            log.info("创建文件夹：{},{}", filePath, destDirectory.mkdirs());
        // 获取实际的文件名
        try (ZipInputStream zis = new ZipInputStream(con.getInputStream())) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File newFile = new File(destDirectory, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs())
                        throw new IOException("Failed to create directory " + newFile);
                    log.info("创建文件夹：{}", newFile);
                } else {
                    // 确保父目录存在
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs())
                        throw new IOException("Failed to create directory " + parent);
                    log.info("创建文件夹：{}", parent);
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile))) {
                        byte[] bytesIn = new byte[4096];
                        int read;
                        while ((read = zis.read(bytesIn)) != -1)
                            bos.write(bytesIn, 0, read);
                    }
                    log.info("下载文件：{}", newFile);
                }
            }
            zis.closeEntry();
        } finally {
            con.disconnect();
        }
    }

    /**
     * getUnDuplicatedFile : 获取不重复的文件名
     *
     * @param folderPath        文件所在文件夹
     * @param suggestedFileName 建议文件名
     * @author xiahaifeng
     */
    public static File getUnDuplicatedFile(String folderPath, String suggestedFileName) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                log.error("创建文件夹失败");
                throw new IllegalArgumentException("创建文件夹失败");
            }
        }
        if (!folder.isDirectory()) {
            log.error("文件夹路径错误");
            throw new IllegalArgumentException("文件夹路径错误");
        }
        File file = new File(folder, suggestedFileName);
        int period = suggestedFileName.lastIndexOf(".");
        if (-1 == period) {
            period = suggestedFileName.length();
        }
        String name = suggestedFileName.substring(0, period);
        String suffix = suggestedFileName.substring(period);
        int i = 0;
        do {
            i++;
            if (!file.exists()) {
                return file;
            }
            file = new File(folder, name + "(" + i + ")" + suffix);
        } while (true);
    }
}
