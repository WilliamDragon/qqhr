package com.qqhr.golden.trade.async;

import java.util.Date;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 16:15
 * @Version 1.0
 */

public class AsyncMessageHead {
    private String messageId;
    private Date date;
    private String messageType;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
