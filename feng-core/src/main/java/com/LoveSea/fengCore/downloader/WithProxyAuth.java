package com.LoveSea.fengCore.downloader;

/**
 * @author xiahaifeng
 */

public record WithProxyAuth(boolean hasAuth,
                            boolean hasProxy,
                            String host,
                            int port,
                            String username,
                            String password) {

}