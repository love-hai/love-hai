package com.loveSea.nettyShow.protobuf;

/**
 * @author xiahaifeng
 */

public class MqInfoToByte {
    public static void main(String[] args) throws Exception {
        MqInfo.MqInfoMessage mqInfoMessage = MqInfo.MqInfoMessage.newBuilder().setMessage("test")
                .setKey("test").setConsumerGroup("test").setTopic("test").setTag("test").setProducerGroup("Test").build().toBuilder().build();
        byte[] bytes = mqInfoMessage.toByteArray();
        MqInfo.MqInfoMessage mqInfoMessage1 = MqInfo.MqInfoMessage.parseFrom(bytes);
        System.out.println(mqInfoMessage1);
    }
}