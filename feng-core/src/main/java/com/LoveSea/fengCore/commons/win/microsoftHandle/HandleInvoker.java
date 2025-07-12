package com.LoveSea.fengCore.commons.win.microsoftHandle;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiahaifeng
 */
@Slf4j
public class HandleInvoker {
    public static HandleInvoker create(Path handlePath) {
        return new HandleInvoker(handlePath);
    }

    private final Path HANDLE_PATH;
    private Path targetPath;
    private String processName;

    private HandleInvoker(Path handlePath) {
        this.HANDLE_PATH = handlePath;
        // 检查是否是 handle.exe 的路径
        if (!handlePath.getFileName().toString().equalsIgnoreCase("handle.exe")) {
            throw new IllegalArgumentException("The provided path is not a valid handle.exe file.");
        }
        if (!handlePath.toFile().exists()) {
            throw new IllegalArgumentException("The provided handle.exe file does not exist: " + handlePath);
        }
        // 示例：GoogleDriveFS.exe  pid: 22724  type: File           31C: C:\Program Files\...
        String regex = "^(.+?)\\s+pid:\\s*(\\d+)\\s+type:\\s*(\\w+)\\s+([0-9A-F]+):\\s+(.+)$";
        this.handelInfoPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public HandleInvoker targetPath(Path targetPath) {
        this.targetPath = targetPath;
        return this;
    }

    public HandleInvoker processName(String processName) {
        this.processName = processName;
        return this;
    }


    public List<HandleInfo> handle() throws IOException {
        List<String> params = new ArrayList<>();
        if (null != this.targetPath) {
            params.add(this.targetPath.toString());
        }
        if (null != this.processName) {
            params.add("-p");
            params.add(this.processName);
        }
        return exeHandle(params);
    }


    private List<HandleInfo> exeHandle(List<String> params) throws IOException {
        List<String> command = new ArrayList<>(params);
        command.add(0, HANDLE_PATH.toString());
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        List<HandleInfo> handleInfos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                HandleInfo handleInfo = parseHandleLine(line);
                if (null != handleInfo) {
                    handleInfos.add(handleInfo);
                }
            }
        }
        return handleInfos;
    }

    private final Pattern handelInfoPattern;

    @Nullable
    private HandleInfo parseHandleLine(String line) {
        Matcher matcher = this.handelInfoPattern.matcher(line);
        if (!matcher.matches()) {
            return null;
        }
        try {
            String processName = matcher.group(1).trim();
            int pid = Integer.parseInt(matcher.group(2));
            String type = matcher.group(3).trim();
            String handleHex = matcher.group(4).trim();
            String filePath = matcher.group(5).trim();
            return new HandleInfo(processName, pid, type, handleHex, filePath);
        } catch (Exception e) {
            log.error("Error parsing handle line: " + line, e);
            return null;
        }
    }
}