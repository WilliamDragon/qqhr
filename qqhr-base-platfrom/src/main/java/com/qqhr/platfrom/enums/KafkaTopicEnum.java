package com.qqhr.platfrom.enums;

/**
 * @Author WilliamDragon
 * @Date 2021/2/1 9:17
 * @Version 1.0
 */

public enum  KafkaTopicEnum {

    SEND_TOPIC_PLATFROM("系统平台交易","topic_platfrom");
    String topicName;
    String topicValue;
    KafkaTopicEnum(String topicName,String topicValue){
        this.topicName = topicName;
        this.topicValue = topicValue;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicValue() {
        return topicValue;
    }
}
