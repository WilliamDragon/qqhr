package com.qqhr.platfrom.dto;

import java.io.Serializable;

/**
 * @Author WilliamDragon
 * @Date 2021/2/1 9:50
 * @Version 1.0
 */

public class KafkaMeaasgeHead implements Serializable {

    private static final long serialVersionUID = -5885732996233469127L;
    private String msgId;//ID
    private String msgType;//类型
    private String msgTimeStamp;//时间戳
    private String msgVersion;//版本号

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgTimeStamp() {
        return msgTimeStamp;
    }

    public void setMsgTimeStamp(String msgTimeStamp) {
        this.msgTimeStamp = msgTimeStamp;
    }

    public String getMsgVersion() {
        return msgVersion;
    }

    public void setMsgVersion(String msgVersion) {
        this.msgVersion = msgVersion;
    }
}
