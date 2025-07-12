/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {

    // handle.exe 路径（建议用绝对路径）
    private static final String HANDLE_PATH = "C:\\Users\\xiaha\\Downloads\\handle.exe";

    // 要检测的路径关键词
    private static final String TARGET_PATH = "D:\\work\\idea\\lalang-browser-2.0\\userData\\1.13.80.136";

    private static final String PROCESS_NAME = "chromium";

    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(HANDLE_PATH, "-p", PROCESS_NAME, TARGET_PATH);
        pb.redirectErrorStream(true); // 合并 stderr 到 stdout
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK")); // 英文系统用 "GBK"，如果乱码可以换 "UTF-8"
        String line;
        Set<Integer> pids = new HashSet<>();
        while ((line = reader.readLine()) != null) {
            System.out.println(line); // 可注释掉
            Integer pid = parsePidFromLine(line);
            if (pid != null) {
                pids.add(pid);
            }
        }
        reader.close();
        for (Integer pid : pids) {
            log.info("pid:{}", pid);
        }
    }

    // 解析 PID
    private static Integer parsePidFromLine(String line) {
        // 示例行: chrome.exe pid: 12345 type: File ...
        if (line.contains("pid:")) {
            try {
                String[] parts = line.split("pid:");
                String pidPart = parts[1].trim().split(" ")[0];
                return Integer.parseInt(pidPart);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    // 杀掉进程
    private static void killProcess(int pid) throws IOException {
        System.out.println("正在尝试结束进程 PID: " + pid);
        Runtime.getRuntime().exec("taskkill /F /PID " + pid);
    }
}
