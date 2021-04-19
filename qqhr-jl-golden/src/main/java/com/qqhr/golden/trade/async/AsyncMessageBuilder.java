package com.qqhr.golden.trade.async;

import com.qqhr.common.enums.TopicEnum;
import com.qqhr.common.utils.Constants;
import com.qqhr.golden.base.BaseRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 15:49
 * @Version 1.0
 * 异步消息构建类
 */

public class AsyncMessageBuilder {

    private static final Logger logger= LoggerFactory.getLogger(AsyncMessageBuilder.class);
    private Class producerClass;//生产者Class
    private Class consumerClass;//消费者Class
    private String consumerMethod;//消费者方法
    private BaseRequestDto requestDto;//参数DTO
    private String messageId;//消息ID
    private String transactionCode;//系统交易码

    private static final String DEFAULT_METHOD_EXECUTE = "execute";

    public static AsyncMessageBuilder newRequest() {
        return new AsyncMessageBuilder();
    }
    public <T> AsyncMessageBuilder withproducerClass(Class<T> producerClass) {
        this.producerClass = producerClass;
        return this;
    }
    public <T> AsyncMessageBuilder withconsumerClass(Class<T> consumerClass) {
        this.consumerClass = consumerClass;
        return this;
    }
    public AsyncMessageBuilder withconsumerMethod(){
        this.consumerMethod = DEFAULT_METHOD_EXECUTE;
        return this;
    }

    public <T extends BaseRequestDto>AsyncMessageBuilder withbaseRequestDto(T requestDto){
        this.requestDto = requestDto;
        return this;
    }

    public AsyncMessageBuilder withmessageId(String messageId){
        this.messageId = messageId;
        return this;
    }
    public AsyncMessageBuilder withtransactionCode(String transactionCode){
        this.transactionCode = transactionCode;
        return this;
    }

    public AsyncMessage build() throws IllegalAccessException {

        if(producerClass == null){
            throw new IllegalAccessException("构建消息生产者为空");
        }

        //创建异步消息头
        AsyncMessageHead asyncMessageHead = new AsyncMessageHead();
        asyncMessageHead.setMessageId(messageId);
        asyncMessageHead.setDate(new Date());
        asyncMessageHead.setMessageType(TopicEnum.TRADE_JOB.gettopicName());

        //requestDto父类 赋值
        this.requestDto.setTranscationCode(Constants.KAFKA_TRANSCATION_CODE);
        this.requestDto.setChannelId(Constants.KAFKA_TRANSCATION_CHANNEL);

        //新建异步消息传送参数,参数均为类的名字以及转Json
        AsyncMessageRequest asyncMessageRequest = new AsyncMessageRequest();
        asyncMessageRequest.setProducerClass(this.producerClass.getName());
        asyncMessageRequest.setConsumerClass(this.consumerClass.getName());
        asyncMessageRequest.setConsumerMethod(this.consumerMethod);
        asyncMessageRequest.setMessageId(this.messageId);
        asyncMessageRequest.setConsumerRequestDtoClass(this.requestDto.getClass().getName());
        asyncMessageRequest.setGetConsumerRequestDto(this.requestDto.toJson());

        //创建异步消息头体，data为“异步消息传送参数”的类信息
        AsyncMessageBody asyncMessageBody = new AsyncMessageBody();
        asyncMessageBody.setDataClass(asyncMessageRequest.getClass().getName());
        asyncMessageBody.setData(asyncMessageRequest.toJson());

        //封装成异步消息类
        AsyncMessage asyncMessage = new AsyncMessage();
        asyncMessage.setAsyncMessageHead(asyncMessageHead);
        asyncMessage.setAsyncMessageBody(asyncMessageBody);

        logger.info("构建后的消息为", asyncMessage.toJson());
        return asyncMessage;
    }

}
