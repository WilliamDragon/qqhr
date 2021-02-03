package com.qqhr.platfrom.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qqhr.common.utils.StringUtil;
import com.qqhr.platfrom.dto.KafkaMeaasgeHead;
import com.qqhr.platfrom.dto.KafkaMessage;
import com.qqhr.platfrom.dto.KafkaMessageBody;
import com.qqhr.platfrom.executor.KafkaMsgValidator;
import com.qqhr.platfrom.service.KafkaSendTemplate;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/1/27 14:34
 * @Version 1.0
 */
@RestController
public class TestkafkaConsumers {

    private static final Logger log= LoggerFactory.getLogger(TestkafkaConsumers.class);
    @Autowired
    private KafkaTemplate template;
    @Autowired
    private KafkaSendTemplate kafkaSendTemplate;
    @Autowired
    KafkaMsgValidator kafkaMsgValidator;
    //topic使用上测试创建的aaaa
    @GetMapping("/sendMsg")
    public String sendMsg(String topic, String message){
        template.send(topic,message);
        return "success";
    }
    @GetMapping("/sendMsg2")
    public void testBatch() throws JsonProcessingException {
        /*for (int i = 0; i < 20; i++) {
            template.send("topic.quick.batch", "test batch listener,dataNum-" + i);
        }*/
        KafkaMeaasgeHead kafkaMeaasgeHead = new KafkaMeaasgeHead();
        kafkaMeaasgeHead.setMsgId("11");
        KafkaMessageBody kafkaMessageBody = new KafkaMessageBody();
        kafkaMessageBody.setMsgTopic("topic.quick.batch");
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setKafkaMeaasgeHead(kafkaMeaasgeHead);
        kafkaMessage.setKafkaMessageBody(kafkaMessageBody);

        for (int i = 20; i < 40; i++) {
            ObjectMapper om = new ObjectMapper();
            HashMap<String, Object> map = new HashMap<>();
            map.put("msgId","222");

            String json = om.writeValueAsString(map);
            String str = "test batch listener,dataNum-" + i;
            kafkaSendTemplate.kaSendTemplate("topic.quick.batch",json);
        }


    }


    @KafkaListener(topics = {"aaaa"})
    public void listen(ConsumerRecord record){
        System.out.println(record.topic()+":"+record.value());
    }

    

    //@KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"topic.quick.batch"},containerFactory = "batchContainerFactory",errorHandler = "consumerAwareErrorHandler")
    public void batchListener(List<String> list, Acknowledgment ack) {

        /*try {
            log.info("topic.quick.batch  receive : ");
            for (String s : list) {
                log.info(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ack.acknowledge();//手动提交偏移量
        }*/
        //后面做处理消息使用
        list.parallelStream().forEach(data -> dealKafkaData(data));
        ack.acknowledge();//手动提交偏移量

    }

    public void dealKafkaData(String data){
        //根据 data处理业务 todo
    /*    JSONObject jsonObject = JSONObject.fromObject(data);
        String ss = jsonObject.getString("msgId");
        String ss1 = jsonObject.getString("msgTimeStamp");*/
        KafkaMessage kafkaMessage = kafkaMsgValidator.parseMsg(data);
        System.out.println("esrdg");
        //根据 data处理业务 todo
    }


    public static void main(String[] args) throws JsonProcessingException {


        ObjectMapper om = new ObjectMapper();
        KafkaMeaasgeHead kafkaMeaasgeHead = new KafkaMeaasgeHead();
        kafkaMeaasgeHead.setMsgId("11");
        kafkaMeaasgeHead.setMsgTimeStamp("123456789");
        kafkaMeaasgeHead.setMsgVersion("01");
        HashMap<String, Object> map = new HashMap<>();
        map.put("msgId","222");

        String json = om.writeValueAsString(map);
        System.out.println(json);

        JSONObject jsonObject = JSONObject.fromObject(json);
        //JSONObject jsonObject = new JSONObject(json);
        String ss = jsonObject.getString("msgId");
        String ss1 = jsonObject.optString("msgTimeStamp");

        if(StringUtil.isNullOrEmpty(ss1)){
            System.out.println("sss 为空");
        }

        String ss2 = jsonObject.getString("msgVersion");



    }
}
