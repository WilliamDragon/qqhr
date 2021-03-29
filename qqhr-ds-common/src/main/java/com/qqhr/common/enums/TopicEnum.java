package com.qqhr.common.enums;

import com.qqhr.common.utils.Constants;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 17:13
 * @Version 1.0
 * kafka主题枚举
 */

public enum TopicEnum {

    TRADE_JOB(Constants.KAFKA_TOPIC_NAME,Constants.KAFKA_TOPIC_VALUE);
    String topicName;
    String topicValue;

    TopicEnum(String topicName, String topicValue) {
        this.topicName = topicName;
        this.topicValue = topicValue;
    }
    public String gettopicName() {
        return topicName;
    }
    public String getTopicValue() {
        return topicValue;
    }

}
