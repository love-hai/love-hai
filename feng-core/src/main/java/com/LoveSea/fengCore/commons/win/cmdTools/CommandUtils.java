package com.LoveSea.fengCore.commons.win.cmdTools;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class CommandUtils {
    // 杀掉进程
    public static String killProcess(int pid) {
        String command = String.format("taskkill /F /PID %d", pid);
        return executeCommand(command);
    }

    /**
     * executeCommand 执行命令
     *
     * @param command 命令
     * @return 执行结果
     */
    public static String executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            StringBuilder output = new StringBuilder();
            try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream(), "GBK"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            return output.toString();
        } catch (Exception e) {
            log.error("Error executing command: {}", command, e);
            return null;
        }
    }

}