package com.qqhr.golden.base;

import com.qqhr.common.base.BaseObject;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 16:50
 * @Version 1.0
 */

public class BaseRequestDto extends BaseObject {
    private String channelId;//渠道id

    private String requestSeq;//外围流水号
    private String transcationCode;//交易码

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getRequestSeq() {
        return requestSeq;
    }

    public void setRequestSeq(String requestSeq) {
        this.requestSeq = requestSeq;
    }

    public String getTranscationCode() {
        return transcationCode;
    }

    public void setTranscationCode(String transcationCode) {
        this.transcationCode = transcationCode;
    }
}
