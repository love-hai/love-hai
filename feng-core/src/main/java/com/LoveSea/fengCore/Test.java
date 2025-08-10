/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {


    public static void main(String[] args) throws IOException {

        Path tempDir = Path.of(System.getProperty("java.io.tmpdir"));
        log.info(tempDir. toString());

    }


}
