package com.qqhr.platfrom.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.BatchAcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 10:59
 * @Version 1.0
 */
@Component
public class MessageDispatcherConsumer implements BatchAcknowledgingMessageListener<String, String> {


    @Override
    public void onMessage(List<ConsumerRecord<String,String>> list, Acknowledgment acknowledgment) {
        System.out.println(list.size());
        acknowledgment.acknowledge();
    }

}
