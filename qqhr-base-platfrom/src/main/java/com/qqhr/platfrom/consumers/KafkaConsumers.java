package com.qqhr.platfrom.consumers;

import com.qqhr.platfrom.dto.KafkaMessage;
import com.qqhr.platfrom.executor.KafkaMsgValidator;
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

    @Autowired
    KafkaMsgValidator kafkaMsgValidator;
    @KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"topic.quick.batch"},containerFactory = "batchContainerFactory",errorHandler = "consumerAwareErrorHandler")
    public void batchListener(List<String> list, Acknowledgment ack) {
        list.parallelStream().forEach(data -> dealKafkaData(data));
        ack.acknowledge();//手动提交偏移量

    }

    public void dealKafkaData(String data){
        KafkaMessage kafkaMessage = kafkaMsgValidator.parseMsg(data);
        System.out.println("kafkaMessage");
        //根据 data处理业务 todo 后续开发
    }
}
