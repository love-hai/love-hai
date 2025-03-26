package com.LoveSea.fengCore.downloader;

import com.LoveSea.fengCore.commons.utils.MyFileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

/**
 * @author xiahaifeng
 */
@Slf4j
public class DataUriTransformFile {
    private final DataUri dataUri;

    protected DataUriTransformFile(DataUri dataUri) {
        this.dataUri = dataUri;
    }

    public File transform(String targetDir, String suggestFileName) {
        return transform(new File(targetDir), suggestFileName);
    }

    public File transform(String targetDir) {
        return transform(new File(targetDir));
    }

    /**
     * transform :  dataUri 转换为文件
     *
     * @param targetDir       文件夹
     * @param suggestFileName 一定会有文件名和后缀名
     */
    public File transform(File targetDir, String suggestFileName) {
        if (targetDir == null || !targetDir.exists() || !targetDir.isDirectory()) {
            log.error("targetDir is not exist or not a directory");
            throw new IllegalArgumentException("targetDir is not exist or not a directory");
        }
        if (suggestFileName == null || !suggestFileName.contains(".")) {
            log.error("suggestFileName is not a file name");
            throw new IllegalArgumentException("suggestFileName is not a file name");
        }
        String fileTempFileName = TempFileName.getTempFileName();
        File file = new File(targetDir, fileTempFileName);
        // 写入文件
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            String encoding =dataUri.getEncoding();
            encoding = encoding.trim().toLowerCase();
            byte[] bytes = switch (encoding){
                case "base64" -> Base64.getDecoder().decode(dataUri.getData());
                case "utf-8" -> dataUri.getData().getBytes();
                default -> throw new RuntimeException("Unknown encoding");
            };
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            log.error("DataUriTransformFile transform error", e);
        }
        File newFileFile = MyFileUtils.getUnDuplicatedFile(targetDir.getPath(), suggestFileName);
        // 重命名文件
        if (!file.renameTo(newFileFile)) {
            log.error("rename file error");
            throw new RuntimeException("rename file error");
        }
        return newFileFile;
    }

    public File transform(File targetDir) {
        String suffix = dataUri.getSubType();
        String suggestFileName = "下载." + suffix;
        return transform(targetDir, suggestFileName);
    }
}