package com.LoveSea.fengCore.study.work;

import lombok.NonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */
public class SolutionMap<T> implements Iterable<Map.Entry<String, QueueTakeSolutionRecord<T>>> {
    private final Map<String, QueueTakeSolutionRecord<T>> map = Collections.synchronizedMap(new LinkedHashMap<>());

    public void put(String key, QueueTakeSolutionRecord<T> value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public QueueTakeSolutionRecord<T> get(String key) {
        return map.get(key);
    }
    @Override
    @NonNull
    public Iterator<Map.Entry<String, QueueTakeSolutionRecord<T>>> iterator() {
        return map.entrySet().iterator();
    }
}