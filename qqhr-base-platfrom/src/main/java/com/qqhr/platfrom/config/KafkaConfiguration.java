package com.qqhr.platfrom.config;

import com.qqhr.platfrom.consumers.MessageDispatcherConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConsumerAwareBatchErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author WilliamDragon
 * @Date 2021/1/27 14:49
 * @Version 1.0
 */
@Configuration
@EnableKafka
public class KafkaConfiguration {

    //消费者配置
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
    @Value("${spring.kafka.consumer.groupIdConfig}")
    private String groupIdConfig;
    //生产者配置
    @Value("${spring.kafka.producer.bootstrapServersConfig}")
    private String bootstrapServersProducerConfig;
    @Value("${spring.kafka.producer.keySerializerClassConfig}")
    private String keySerializerClassConfig;
    @Value("${spring.kafka.producer.valueSerializerClassConfig}")
    private String valueSerializerClassConfig;
    //ConcurrentKafkaListenerContainerFactory为创建Kafka监听器的工程类，这里只配置了消费者
    //kafka 监听工厂
    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    //kafka 监听容器, 有了监听工厂进而才能创建监听容器
    @Bean
    public KafkaMessageListenerContainer demoListenerContainer() {
        ContainerProperties properties = new ContainerProperties("topic.quick.bean");
        properties.setGroupId("bean");
        properties.setMessageListener(new MessageListener<Integer,String>() {
            private Logger log = LoggerFactory.getLogger(this.getClass());
            @Override
            public void onMessage(ConsumerRecord<Integer, String> record) {
                log.info("topic.quick.bean receive : " + record.toString());
            }
        });

        return new KafkaMessageListenerContainer(consumerFactory(), properties);
    }

    //根据consumerProps填写的参数创建消费者工厂
    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    //根据senderProps填写的参数创建生产者工厂
    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(senderProps());
    }

    //kafkaTemplate实现了Kafka发送接收等功能
    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() {
        KafkaTemplate template = new KafkaTemplate<Integer, String>(producerFactory());
        return template;
    }

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

    //消费者Map
    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdConfig);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommitConfig);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMsConfig);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecordsConfig);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClassConfig);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClassConfig);
        return props;
    }
    //生产者Map
    private Map<String, Object> senderProps (){
        Map<String, Object> props = new HashMap<>();
        //连接地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersProducerConfig);
        //重试，0为不启用重试机制
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        //控制批处理大小，单位为字节
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        //生产者可以使用的总内存字节来缓冲等待发送到服务器的记录
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024000);
        //键的序列化方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClassConfig);
        //值的序列化方式
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClassConfig);
        return props;
    }



}
