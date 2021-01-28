package com.qqhr.platfrom.consumers;

import com.qqhr.platfrom.config.BatchListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/1/27 14:34
 * @Version 1.0
 */
@RestController
public class TestkafkaConsumers {

    private static final Logger log= LoggerFactory.getLogger(BatchListener.class);
    @Autowired
    private KafkaTemplate template;
    //topic使用上测试创建的aaaa
    @GetMapping("/sendMsg")
    public String sendMsg(String topic, String message){
        template.send(topic,message);
        return "success";
    }
    @GetMapping("/sendMsg2")
    public void testBatch() {
        for (int i = 0; i < 20; i++) {
            template.send("topic.quick.batch", "test batch listener,dataNum-" + i);
        }
    }
    
    
    @KafkaListener(topics = {"aaaa"})
    public void listen(ConsumerRecord record){
        System.out.println(record.topic()+":"+record.value());
    }

    

    @KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"topic.quick.batch"},containerFactory = "batchContainerFactory")
    public void batchListener(List<String> list, Acknowledgment ack) {

        list.parallelStream().forEach(data -> dealKafkaData(data));

        ack.acknowledge();//手动提交偏移量

    }

    public void dealKafkaData(String data){
        //根据 data处理业务 todo
    }



   /* @KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"topic.quick.batch"},containerFactory = "batchContainerFactory")
    public void batchListener(List<String> data) {

        log.info("topic.quick.batch  receive : ");
        for (String s : data) {
            log.info(s);
        }
    }*/
}
