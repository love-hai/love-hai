/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {


    // 源目录
    private static final String SOURCE_DIR = "D:\\work\\idea\\love-hai\\feng-core\\src\\main\\java\\com\\LoveSea\\fengCore\\study\\leetCode";
    // 目标根目录
    private static final String TARGET_DIR = "D:\\work\\idea\\love-hai\\leetCode\\src\\main\\java\\com\\lovehai\\leetcode";

    public static void main(String[] args) throws IOException {
        File sourceDir = new File(SOURCE_DIR);
        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            System.out.println("源目录不存在或不是文件夹");
            return;
        }

        File targetRoot = new File(TARGET_DIR);
        if (!targetRoot.exists()) {
            targetRoot.mkdirs();
        }

        // 获取所有 Solution 文件
        File[] files = sourceDir.listFiles((dir, name) -> name.matches("Solution\\d+\\.java"));
        if (files == null || files.length == 0) {
            System.out.println("没有找到 Solution 文件");
            return;
        }
        // 按文件名中的数字排序
        Arrays.sort(files, Comparator.comparingInt(v -> extractNumber(v)));
        for (File file : files) {
            int num = extractNumber(file);
            String subDirName = getSubDirName(num);
            if (subDirName == null) {
                System.out.println("文件 " + file.getName() + " 超出指定范围，跳过");
                continue;
            }
            File targetDir = new File(targetRoot, subDirName);
            if (!targetDir.exists()) targetDir.mkdirs();

            File targetFile = new File(targetDir, file.getName());
            Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("复制 " + file.getName() + " 到 " + targetDir.getAbsolutePath());
        }
    }

    // 从文件名提取数字，例如 Solution123.java -> 123
    private static int extractNumber(File file) {
        String name = file.getName();
        String numStr = name.replaceAll("\\D+", "");
        return Integer.parseInt(numStr);
    }

    // 根据题号确定目标子目录
    private static String getSubDirName(int num) {
        int start = num / 100 * 100 + 1;
        int end = start + 100 - 1;
        String s = String.valueOf(start);
        while (s.length() < 4) {
            s = "0" + s;
        }
        String e = String.valueOf(end);
        while (e.length() < 4) {
            e = "0" + e;
        }
        return "_" + s + "_" + e;
    }

}
