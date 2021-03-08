package com.qqhr.po;

/**
 * @Author WilliamDragon
 * @Date 2021/3/5 16:36
 * @Version 1.0
 */

public class KafkaMessageInfo {

    private String messageId;
    private String topic;
    private String parition;
    private String messageType;
    private String sendTime;
    private String reviceTime;
    private String messageStatus;
    private String messageData;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getParition() {
        return parition;
    }

    public void setParition(String parition) {
        this.parition = parition;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReviceTime() {
        return reviceTime;
    }

    public void setReviceTime(String reviceTime) {
        this.reviceTime = reviceTime;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }
}
