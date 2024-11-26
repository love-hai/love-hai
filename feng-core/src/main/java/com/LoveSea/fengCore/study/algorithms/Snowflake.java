package com.LoveSea.fengCore.study.algorithms;

/**
 * @author xiahaifeng
 */

public class Snowflake {

    // 开始时间戳
    private final long twepoch = 1732583798000L;

    // 数据中心标识id所占的位数

    private final long datacenterIdBits = 5L;

    // 机器id所占的位数
    private final long workerIdBits = 5L;
    // 数据中心标识id最大值
    private final long maxDatacenterId = ~(-1L << datacenterIdBits);

    // 机器id最大值
    private final long maxWorkerId = ~(-1L << workerIdBits);
    // 自增序列所占的位数
    private final long sequenceBits = 12L;
    // 时间戳左移位数
    private final long timestampOffset = sequenceBits + workerIdBits + datacenterIdBits;
    // 数据中心标识id左移位数
    private final long datacenterIdOffset = sequenceBits + workerIdBits;
    // 机器id左移位数
    private final long workerIdOffset = sequenceBits;
    // 自增序列掩码
    private final long sequenceMask = ~(-1L << sequenceBits);
    // 自增序列
    private long sequence = 0L;
    // 上次时间戳
    private long lastTimestamp = -1L;
    // 数据中心标识id
    private final long datacenterId;
    // 机器id
    private final long workerId;

    public Snowflake(long datacenterId, long workerId) {
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    public synchronized long generateId() {
        // 获取当前时间戳
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            // 如果当前时间戳小于上次时间戳，可能时钟回拨，继续沿用上次时间戳
            timestamp = lastTimestamp;
        }
        if (lastTimestamp == timestamp) {
            // 如果当前时间戳等于上次时间戳，序列号+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 如果序列号大于等于 2^sequenceBits，等待下一毫秒
                timestamp = nextMillis(lastTimestamp);
                lastTimestamp = timestamp;
            }
        } else {
            // 如果当前时间戳大于上次时间戳，序列号重置为0
            sequence = 0;
            lastTimestamp = timestamp;
        }
        return ((timestamp - twepoch) << timestampOffset) | (datacenterId << datacenterIdOffset) | (workerId << workerIdOffset) | sequence;
    }

    private long nextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}