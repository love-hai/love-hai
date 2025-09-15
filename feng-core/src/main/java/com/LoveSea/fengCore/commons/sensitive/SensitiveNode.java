package com.LoveSea.fengCore.commons.sensitive;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author xiahaifeng
 */

public class SensitiveNode {
    private boolean is_end;
    private Map<Character, SensitiveNode> children;

    public boolean isEnd() {
        return is_end;
    }

    public SensitiveNode addChild(char nodeValue, boolean isEnd) {
        if (null == children) {
            children = new HashMap<>();
        }
        if (children.containsKey(nodeValue)) {
            return children.get(nodeValue);
        }
        SensitiveNode node = new SensitiveNode();
        node.is_end = isEnd;
        children.put(nodeValue, node);
        return node;
    }

    public Optional<SensitiveNode> getChild(char value) {
        if (null == children) {
            return Optional.empty();
        }
        return Optional.ofNullable(children.get(value));
    }
}