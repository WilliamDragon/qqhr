package com.qqhr.platfrom.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author WilliamDragon
 * @Date 2021/1/27 17:27
 * @Version 1.0
 */

@Component
public class BatchListener {

    private static final Logger log= LoggerFactory.getLogger(BatchListener.class);
    @Value("${spring.kafka.consumer.bootstrapServersConfig}")
    private String bootstrapServersConfig;
    @Value("${spring.kafka.consumer.enableAutoCommitConfig}")
    private String enableAutoCommitConfig;
    @Value("${spring.kafka.consumer.autoCommitIntervalMsConfig}")
    private String autoCommitIntervalMsConfig;
    @Value("${spring.kafka.consumer.keyDeserializerClassConfig}")
    private String keyDeserializerClassConfig;
    @Value("${spring.kafka.consumer.valueDeserializerClassConfig}")
    private String valueDeserializerClassConfig;
    @Value("${spring.kafka.consumer.maxPollRecordsConfig}")
    private String maxPollRecordsConfig;
    @Value("${spring.kafka.consumer.maxPollIntervalMsConfig}")
    private String maxPollIntervalMsConfig;
    @Value("${spring.kafka.consumer.concurrency}")
    private Integer concurrency;

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommitConfig);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMsConfig);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecordsConfig);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClassConfig);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClassConfig);
        return props;
    }

    /*@Bean("batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory listenerContainer() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps()));
        //设置并发量，小于或等于Topic的分区数
        container.setConcurrency(5);
        //设置为批量监听
        container.setBatchListener(true);
        return container;
    }*/

    @Bean("batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, byte[]> batchKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps()));

        factory.setConcurrency(concurrency);
        factory.setBatchListener(true);//设置批量
        factory.getContainerProperties().setPollTimeout(Long.parseLong(autoCommitIntervalMsConfig));
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);//设置提交偏移量的方式
        return factory;
    }
    @Bean
    public NewTopic batchTopic() {
        return new NewTopic("topic.quick.batch", 8, (short) 1);
    }




}
