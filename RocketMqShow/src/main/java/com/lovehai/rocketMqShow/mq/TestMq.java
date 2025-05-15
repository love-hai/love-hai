package com.lovehai.rocketMqShow.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author xiahaifeng
 */
@Slf4j
@Component
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",
        topic = "${rocketmq.test.topic}",
        consumerGroup = "TestMq",
        consumeThreadMax = 1)
public class TestMq implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        while (true) {
            try {
                log.info("========TestMq====接收消息:{}", s);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("========TestMq====接收消息失败:{}", e.getMessage(), e);
            }
        }
    }
}