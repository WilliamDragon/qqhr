package com.qqhr.platfrom.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qqhr.common.utils.StringUtil;
import com.qqhr.platfrom.dto.KafkaMeaasgeHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author WilliamDragon
 * @Date 2021/2/1 9:34
 * @Version 1.0
 */
@Service
public class KafkaSendTemplate {

    private static final Logger log= LoggerFactory.getLogger(KafkaSendTemplate.class);
    @Autowired
    private KafkaTemplate template;

    /**
     * 为方法为Kafka发送消息入口，kafkaMessage可以是自定义Bean，或者Map，后续根据实际情况可定制 开发发送消息的模板
     * 有关Kafka消费者的校验都是举个例子，后续可根据实际情况做和业务相关的校验。
     * @param topic
     * @param kafkaMessage
     */
    @GetMapping("/kaSendTemplate")
    public void kaSendTemplate(String topic, Object kafkaMessage){

        log.info("step1:"+"开始发送消息"+ kafkaMessage);
        try{
            ObjectMapper om = new ObjectMapper();
            String jsonKafkaMessage = om.writeValueAsString(kafkaMessage);
            log.info("解析消息："+ jsonKafkaMessage);
            if(validData(topic,jsonKafkaMessage)){
                ListenableFuture<SendResult<String, Object>> future = template.send(topic, jsonKafkaMessage);
                future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        //发送失败的处理
                        log.info("step3:"+"发送消息失败"+kafkaMessage+throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                        //发送成功的处理
                        log.info("step2:"+"发送消息成功"+ kafkaMessage+ stringObjectSendResult.toString());
                    }
                });
            }
        }catch (Exception e){
            log.info("step4:"+"发送消息失败"+kafkaMessage);
            e.printStackTrace();
        }

    }
    //校验消息
    public Boolean validData(String topic, String kafkaMessage){
        Boolean result = true;
        StringBuffer validMessage = null;
        if(StringUtil.isNullOrEmpty(topic)){
            result = false;
            validMessage.append(topic+"isNullOrEmpty");
        }
        log.info(StringUtil.isNullOrEmpty(validMessage)? "step5: 消息校验成功" : "step5: 消息校验失败,请检查消息是否正确"+ validMessage);
        return result;
    }

}
