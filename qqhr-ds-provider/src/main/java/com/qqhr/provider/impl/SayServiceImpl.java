package com.qqhr.provider.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qqhr.api.SayService;
import com.qqhr.dao.mapper.UserMapper;
import com.qqhr.dto.Xua01101RequestDto;
import com.qqhr.dto.Xua01101ResponseDto;
import com.qqhr.entity.User;

import com.qqhr.platfrom.dto.KafkaMeaasgeHead;
import com.qqhr.platfrom.dto.KafkaMessage;
import com.qqhr.platfrom.dto.KafkaMessageBody;
import com.qqhr.platfrom.executor.KafkaMsgValidator;
import com.qqhr.platfrom.service.KafkaSendTemplate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SayServiceImpl implements SayService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public String sayHello(String str) {
        List<User> all = userMapper.findAll();
        System.out.println("sef");
        return str;
    }

    @Autowired
    private KafkaTemplate template;
    @Autowired
    private KafkaSendTemplate kafkaSendTemplate;
    @Autowired
    KafkaMsgValidator kafkaMsgValidator;
    //分割线为展示service层调用Kafka发送消息的使用方法
    @Override
    public List<Xua01101ResponseDto> listUser(Map param) {

        List<User> list = userMapper.findListUser(param);
        Xua01101ResponseDto xua01101ResponseDto = new Xua01101ResponseDto();
        BeanUtils.copyProperties(list.get(0),xua01101ResponseDto);
        List<Xua01101ResponseDto> result= new ArrayList<Xua01101ResponseDto>();
        result.add(xua01101ResponseDto);
         //========================================
        KafkaMeaasgeHead kafkaMeaasgeHead = new KafkaMeaasgeHead();
        kafkaMeaasgeHead.setMsgId("11");
        KafkaMessageBody kafkaMessageBody = new KafkaMessageBody();
        kafkaMessageBody.setMsgTopic("topic.quick.batch");
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setKafkaMeaasgeHead(kafkaMeaasgeHead);
        kafkaMessage.setKafkaMessageBody(kafkaMessageBody);

        for (int i = 20; i < 21; i++) {
            ObjectMapper om = new ObjectMapper();
            HashMap<String, Object> map = new HashMap<>();
            map.put("msgId","222");
            map.put("msgTopic","12345678");

            try{
                String json = om.writeValueAsString(map);
            }catch(Exception e){
                e.printStackTrace();
            }

            String str = "test batch listener,dataNum-" + i;
            kafkaSendTemplate.kaSendTemplate("topic.quick.batch",map);
        }
        //===============================================
        return result;
    }

    /*@KafkaListener(topics = {"aaaa"})
    public void listen(ConsumerRecord record){
        System.out.println(record.topic()+":"+record.value());
    }*/


}
