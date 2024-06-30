package com.loveSea.uidServer.service;

/**
 * @author xiahaifeng
 */
import com.LoveSea.fengCore.retryable.Retryable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UidGenerator {

    // 区域ID最大值：7
    // private final static long maxRegionId = ~(-1L << 3);
    // 机器ID最大值：31
    // private final static long maxWorkerId = ~(-1L << 5);
    // 业务ID最大值：31
    // private final static long maxBusinessId = ~(-1L << 5);
    // 序列号最大值：1023
    private final static long sequenceMask = ~(-1L << 10);

    // 时间毫秒左移23位
    private final static long timestampShift = 23;
    // 业务ID左移18位
    private final static long businessIdShift = 10;
    // 区域ID左移15位
    private final static long regionIdShift = 15;
    // 机器ID左移10位
    private final static long workerIdShift = 10;

    private static long lastTimestamp = -1L;

    // 序列号
    private long sequence = 0L;
    private final long regionId = 10;
    private final long workerId = 5;

    @Retryable(maxRetries = 3, delay = 1000)
    public long generate(Integer tableId) {
        return this.nextId(tableId);
    }

    private synchronized long nextId(int tableId) {
        long timestamp = currentTimestamp();

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = nextMilli();
            }
        } else {
            sequence = new SecureRandom().nextInt(10);
        }
        lastTimestamp = timestamp;

        // 基准时间：2017-01-01 08:00
        return ((timestamp - 1483228800000L) << timestampShift) | (regionId << regionIdShift) | (workerId << workerIdShift) | ((long) tableId << businessIdShift) | sequence;
    }

    private long nextMilli() {
        long timestamp = this.currentTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = this.currentTimestamp();
        }
        return timestamp;
    }

    private long currentTimestamp() {
        return System.currentTimeMillis();
    }
}
