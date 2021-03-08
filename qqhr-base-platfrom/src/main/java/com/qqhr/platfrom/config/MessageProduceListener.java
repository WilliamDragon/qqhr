package com.qqhr.platfrom.config;

import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @Author WilliamDragon
 * @Date 2021/3/4 11:19
 * @Version 1.0
 */
public class MessageProduceListener implements ProducerListener {

    public void onSuccess(String topic, Integer par){

    }
}
