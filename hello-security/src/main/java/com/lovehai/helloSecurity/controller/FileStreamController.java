package com.lovehai.helloSecurity.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author xiahaifeng
 */
@RestController
@RequestMapping("/file/stream")
@Slf4j
public class FileStreamController {

    @GetMapping("")
    public void fileStream(HttpServletResponse response) {
        File old = new File("D:\\tool\\Tencent\\qidianDocument\\3005839498\\FileRecv\\数据源模板\\TK海外仓补货表.xlsx");
        try (FileInputStream in = new FileInputStream(old);
             OutputStream os = response.getOutputStream()) {
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