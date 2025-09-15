package com.LoveSea.fengCore.commons.sensitive;

import org.junit.jupiter.api.Test;

import java.io.*;

class SensitiveWordGroupTest {

    @Test
    void filter() throws IOException {
        SensitiveWordGroup sensitiveWordGroup = new SensitiveWordGroup();
        // 读取 资源text的 反动词库.txt 列表
        String path = ("src/test/resources/反动词库.txt");
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
        try (var inputStream = new FileInputStream(file)) {
            var reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sensitiveWordGroup.add(line);
            }
        }
        String t1 = "这是一个敏感的测试，看看能不能屏蔽敏感词。";
        String t2 = "The government is bad, apple is fine but bad words should be masked.";
        String t3 = "他在街上喊：打倒共产党！";
        String t4 = "请愿活动演变成了大规模的游行 youxing";
        String t5 = "今天上网用了自由门，还访问了wikipedia。";
        String t6 = "一些符号插入敏@感#词，看能否识别。";
        String t7 = "大小写混合 BAD baD Bad，应该都被屏蔽。";
        String t8 = "空字符串测试：'' 结果应该还是空。";
        String t9 = "多个重复词：敏感敏感badbadappleapple";
        String t10 = "正常文本，没有敏感词。";
        String[] tests = {t1, t2, t3, t4, t5, t6, t7, t8, t9, t10};
        for (String test : tests){
            System.out.println(sensitiveWordGroup.filter(test));
        }
    }
}