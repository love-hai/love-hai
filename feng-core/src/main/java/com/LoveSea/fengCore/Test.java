/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("((\\d+\\.)*\\d+).jar");
        String jarName = "1.0.0.jar";
        Matcher matcher = pattern.matcher(jarName);
       if (matcher.matches()){
      log.info("{}",matcher.group(0));
           for (int i = 0; i < matcher.groupCount(); i++){
               log.info("{}",matcher.group(i+1));
           }
       }
    }

}
