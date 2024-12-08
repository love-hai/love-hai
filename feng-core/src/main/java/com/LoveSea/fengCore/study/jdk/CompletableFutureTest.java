package com.LoveSea.fengCore.study.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author xiahaifeng
 */
@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello World");
        future.thenAccept(log::info).exceptionally(e -> {
            log.error("error", e);
            return null;
        });
    }


}