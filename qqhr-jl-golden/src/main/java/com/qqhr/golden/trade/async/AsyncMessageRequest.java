package com.qqhr.golden.trade.async;

import com.qqhr.common.base.BaseObject;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 17:58
 * @Version 1.0
 * 交易RequestDto
 */

public class AsyncMessageRequest extends BaseObject {
    private String messageId;
    private String producerClass;
    private String consumerClass;
    private String consumerMethod;
    private String consumerRequestDtoClass;
    private String getConsumerRequestDto;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getProducerClass() {
        return producerClass;
    }

    public void setProducerClass(String producerClass) {
        this.producerClass = producerClass;
    }

    public String getConsumerClass() {
        return consumerClass;
    }

    public void setConsumerClass(String consumerClass) {
        this.consumerClass = consumerClass;
    }

    public String getConsumerMethod() {
        return consumerMethod;
    }

    public void setConsumerMethod(String consumerMethod) {
        this.consumerMethod = consumerMethod;
    }

    public String getConsumerRequestDtoClass() {
        return consumerRequestDtoClass;
    }

    public void setConsumerRequestDtoClass(String consumerRequestDtoClass) {
        this.consumerRequestDtoClass = consumerRequestDtoClass;
    }

    public String getGetConsumerRequestDto() {
        return getConsumerRequestDto;
    }

    public void setGetConsumerRequestDto(String getConsumerRequestDto) {
        this.getConsumerRequestDto = getConsumerRequestDto;
    }
}
