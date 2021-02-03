package com.qqhr.platfrom.service;

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
    @GetMapping("/kaSendTemplate")
    public void kaSendTemplate(String topic, String kafkaMessage){

        log.info("step1:"+"开始发送消息"+ kafkaMessage);
        try{
            if(validData(topic,kafkaMessage)){
                ListenableFuture<SendResult<String, Object>> future = template.send(topic, kafkaMessage);
                future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        //发送失败的处理
                        log.info("step3:"+"发送消息失败"+kafkaMessage.toString()+throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                        //发送成功的处理
                        log.info("step2:"+"发送消息成功"+ kafkaMessage.toString()+ stringObjectSendResult.toString());
                    }
                });
            }
        }catch (Exception e){
            log.info("step4:"+"发送消息失败"+kafkaMessage.toString());
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
