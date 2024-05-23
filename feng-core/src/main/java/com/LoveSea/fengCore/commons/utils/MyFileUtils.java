package com.LoveSea.fengCore.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
@Slf4j
public class MyFileUtils {

    public static void main(String[] args) {
        String path = "D:\\test\\test.txt";
        System.out.println(path.substring(path.lastIndexOf(".")));
        System.out.println(path.substring(0, path.lastIndexOf(".")));
    }

    /**
     * MethodName: coverOldFile <br>
     * Description: 覆盖旧文件<br>
     * @param urlString      java.lang.String      :
     * @param exportFilePath java.lang.String      :
     * @param fileName       java.lang.String      :
     * @param isAppend       java.lang.Boolean...  :
     * @author xiahaifeng
     * @since 2024/2/29 13:45
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
     * MethodName: downloadFile <br>
     * Description: 下载文件<br>
     * @param urlString      java.lang.String      :
     * @param exportFilePath java.lang.String      :
     * @param fileName       java.lang.String      :
     * @param isAppend       java.lang.Boolean...  :
     * @author xiahaifeng
     * @since 2024/2/29 13:45
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
     * MethodName: getFileName <br>
     * Description: 获取正确的文件名<br>
     * @param path java.lang.String  :
     * @return java.lang.String
     * @author xiahaifeng
     * @since 2024/2/29 13:45
     */
    private String getFileName(String path) {
        if (MyStringUtils.isEmpty(path)) {
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

}
