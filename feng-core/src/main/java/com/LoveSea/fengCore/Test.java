/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        File old = new File("D:\\tool\\Tencent\\qidianDocument\\3005839498\\FileRecv\\数据源模板\\TK海外仓补货表.xlsx");
        File newFile = new File("D:\\tool\\Tencent\\qidianDocument\\3005839498\\FileRecv\\数据源模板\\TK海外仓补货表_new.xlsx");

        try (FileInputStream in = new FileInputStream(old);
             FileOutputStream os = new FileOutputStream(newFile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
