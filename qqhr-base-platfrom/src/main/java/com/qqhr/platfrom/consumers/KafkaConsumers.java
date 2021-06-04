package com.qqhr.platfrom.consumers;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qqhr.common.enums.TopicEnum;
import com.qqhr.common.utils.JsonUtils;
import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.golden.trade.async.AsyncMessageRequest;
import com.qqhr.platfrom.config.ErrorListener;
import com.qqhr.platfrom.dispatcher.MsgDispatcher;
import com.qqhr.platfrom.dto.KafkaMessage;
import com.qqhr.platfrom.executor.ExecutorPipeline;
import com.qqhr.platfrom.executor.KafkaMsgValidator;
import com.qqhr.platfrom.filter.FilterChain;
import com.qqhr.platfrom.handler.HandlerMapping;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/2/3 15:40
 * @Version 1.0
 */
@Service
public class KafkaConsumers {

    private static final Logger log= LoggerFactory.getLogger(KafkaConsumers.class);
    /**
     * topics 现写为固定“topic.quick.batch”，后续可根据实际情况 换做枚举更好
     */
    @Autowired
    KafkaMsgValidator kafkaMsgValidator;
    @Autowired
    private FilterChain filterChain;
    @Autowired
    HandlerMapping handlerMapping;

    @Autowired
    private MsgDispatcher msgDispatcher;
    /*@KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"topic.quick.batch"},containerFactory = "batchContainerFactory",errorHandler = "consumerAwareErrorHandler")
    public void batchListener(List<String> list, Acknowledgment ack) {

        list.parallelStream().forEach(data -> dealKafkaData(data));
        ack.acknowledge();//手动提交偏移量

    }*/
    @KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"topic.quick.batch","guojinlongTopic"},containerFactory = "batchContainerFactory",errorHandler = "consumerAwareErrorHandler")
    public void batchListener(List<ConsumerRecord<String,String>> list, Acknowledgment ack) {

        System.out.println(list);
        list.parallelStream().forEach(data -> doTransaction(data));
        ack.acknowledge();//手动提交偏移量

    }

    public void doTransaction(ConsumerRecord<String,String> record){
        String str = record.value();
        try{
            dealKafkaData(str);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void dealKafkaData(String data) throws ClassNotFoundException {

        Gson gson = new Gson();
        AsyncMessage asyncMessage = gson.fromJson(data, AsyncMessage.class);
        System.out.println(asyncMessage);

        String bodyData = asyncMessage.getAsyncMessageBody().getData();
        String bodyDataClass =  asyncMessage.getAsyncMessageBody().getDataClass();
        AsyncMessageRequest asyncMessageRequest = (AsyncMessageRequest) JsonUtils.toObject(bodyData, Class.forName(bodyDataClass));

        msgDispatcher.dispatcher(asyncMessageRequest);


       /* //执行链 进行过滤
        this.filterChain.filter(asyncMessage);

        //寻找select执行器 和执行链大同小异
        ExecutorPipeline executorPipeline = this.handlerMapping.doSelectExecutorPipeline(TopicEnum.TRADE_JOB.gettopicName());
        if(executorPipeline != null){
            executorPipeline.handle(asyncMessage);
        }
        //如果需要后续在对消息进行处理，则可以在写一个执行链，和filter一样（PostProcess）
        //this.postProcessChain.postProcess(asyncMessage);  todo


        KafkaMessage kafkaMessage = kafkaMsgValidator.parseMsg(data);
        log.info("topic.quick.batch  receive : "+ data);
        System.out.println("kafkaMessage");*/
        //根据 data处理业务 todo 后续开发
    }
}
