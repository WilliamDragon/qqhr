package com.qqhr.platfrom.executor;

import com.qqhr.common.utils.StringUtil;
import com.qqhr.platfrom.consumers.TestkafkaConsumers;
import com.qqhr.platfrom.dto.KafkaMeaasgeHead;
import com.qqhr.platfrom.dto.KafkaMessage;
import com.qqhr.platfrom.dto.KafkaMessageBody;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author WilliamDragon
 * @Date 2021/2/3 11:18
 * @Version 1.0
 */
@Component
public class KafkaMsgValidator {
    private static final Logger log= LoggerFactory.getLogger(TestkafkaConsumers.class);

    /**
     * 解析kafka消息 转换成 KafkaMessage Bean
     * @param data 为json类型
     * @return
     */
    public KafkaMessage parseMsg(String data){
        KafkaMessage kafkaMessage = null;
        try{
            log.info("Kafka 解析jsonk开始:"+ data);
            JSONObject jsonObject = JSONObject.fromObject(data);

            if(!StringUtil.isNullOrEmpty(data) && doVaildate(jsonObject)){
                KafkaMeaasgeHead kafkaMeaasgeHead = new KafkaMeaasgeHead();
                kafkaMeaasgeHead.setMsgId(jsonObject.getString("msgId"));

                KafkaMessageBody kafkaMessageBody = new KafkaMessageBody();
                kafkaMessageBody.setMsgTopic(jsonObject.getString("msgTopic"));

                kafkaMessage = new KafkaMessage();
                kafkaMessage.setKafkaMeaasgeHead(kafkaMeaasgeHead);
                kafkaMessage.setKafkaMessageBody(kafkaMessageBody);
            }else{
                log.info("Kafka解析json结束:数据格式错误"+ data);
            }
        }catch (Exception e){
            log.error("MsgValidatorParse.parse",e);
        }
        return kafkaMessage;

    }
    public boolean doVaildate(JSONObject jsonObject){
        //optString()方法为空不会报错, getString()方法为空会报错
        if(StringUtil.isNullOrEmpty(jsonObject.optString("msgId"))){
            log.info("消息格式错误：缺少msgId字段");
            return false;
        }
        if(StringUtil.isNullOrEmpty(jsonObject.optString("msgTopic"))){
            log.info("消息格式错误：缺少msgTopic字段");
            return false;
        }
        return true;
    }
}
