package com.qqhr.platfrom.config;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @Author WilliamDragon
 * @Date 2021/3/4 11:19
 * @Version 1.0
 */
@Component
public class MessageProduceListener implements ProducerListener {

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        System.out.println(producerRecord);
    }
}
