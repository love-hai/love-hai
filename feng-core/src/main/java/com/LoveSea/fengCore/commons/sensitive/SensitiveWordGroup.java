package com.LoveSea.fengCore.commons.sensitive;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author xiahaifeng
 */

public class SensitiveWordGroup {
    private final SensitiveNode root;

    public SensitiveWordGroup() {
        root = new SensitiveNode();
    }

    public void add(String word) {
        SensitiveNode node = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            node = node.addChild(chars[i], i == chars.length - 1);
        }
    }

    public boolean hasSensitiveWord(String text) {
        char[] chars = text.toCharArray();
        int index = 0;
        while (index < chars.length) {
            int sensitiveWordEnd = findSensitiveWordEnd(chars, root, index);
            if (sensitiveWordEnd != -1) {
                return true;
            } else {
                index++;
            }
        }
        return false;
    }

    public List<String> findAll(String text) {
        List<String> sensitiveWords = new ArrayList<>();
        char[] chars = text.toCharArray();
        int index = 0;
        while (index < chars.length) {
            int sensitiveWordEnd = findSensitiveWordEnd(chars, root, index);
            if (sensitiveWordEnd != -1) {
                sensitiveWords.add(text.substring(index, sensitiveWordEnd + 1));
                index = sensitiveWordEnd + 1;
            } else {
                index++;
            }
        }
        return sensitiveWords;
    }

    public String filter(String text) {
        char[] chars = text.toCharArray();
        int index = 0;
        while (index < chars.length) {
            int sensitiveWordEnd = findSensitiveWordEnd(chars, root, index);
            if (sensitiveWordEnd != -1) {
                for (int i = sensitiveWordEnd; i >= index; i--) {
                    chars[i] = '*';
                }
                index = sensitiveWordEnd + 1;
            } else {
                index++;
            }
        }
        return new String(chars);
    }

    private int findSensitiveWordEnd(char[] chars, SensitiveNode node, int start) {
        char ch = chars[start];
        Optional<SensitiveNode> optional = node.getChild(ch);
        if (optional.isPresent()) {
            SensitiveNode child = optional.get();
            if (child.isEnd()) {
                return start;
            } else {
                return findSensitiveWordEnd(chars, child, start + 1);
            }
        }
        return -1;
    }
}