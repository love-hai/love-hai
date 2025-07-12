package com.LoveSea.fengCore.commons.win.microsoftHandle;

/**
 * @author xiahaifeng
 */


public class HandleInfo {
    private String processName;
    private int pid;
    private String type;
    private String handleHex;
    private String filePath;

    public HandleInfo(String processName, int pid, String type, String handleHex, String filePath) {
        this.processName = processName;
        this.pid = pid;
        this.type = type;
        this.handleHex = handleHex;
        this.filePath = filePath;
    }

    public String getProcessName() {
        return processName;
    }

    public int getPid() {
        return pid;
    }

    public String getType() {
        return type;
    }

    public String getHandleHex() {
        return handleHex;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return String.format("[%s] pid: %d type: %s handle: %s path: %s",
                processName, pid, type, handleHex, filePath);
    }
}
