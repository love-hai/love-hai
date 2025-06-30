/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {


    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        CompletableFuture.runAsync(() -> {
            try {
                while (true) {
                    Integer a = queue.take();
                    log.info("a:{}", a);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            queue.put(i);
        }
        // 想让他中断
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
        }

    }
}
