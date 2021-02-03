package com.qqhr.platfrom.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author WilliamDragon
 * @Date 2021/2/1 9:50
 * @Version 1.0
 */

public class KafkaMessageBody implements Serializable {

    private static final long serialVersionUID = -1859316086274413387L;
    private String msgDate;//消息时间
    private String msgTopic;//消息Topic
    private Map msgBody;//消息体

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public Map getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(Map msgBody) {
        this.msgBody = msgBody;
    }

    public String getMsgTopic() {
        return msgTopic;
    }

    public void setMsgTopic(String msgTopic) {
        this.msgTopic = msgTopic;
    }
}
