package com.qqhr.provider.impl;

import com.qqhr.api.SayService;
import com.qqhr.dao.mapper.UserMapper;
import com.qqhr.dto.Xua01101RequestDto;
import com.qqhr.dto.Xua01101ResponseDto;
import com.qqhr.entity.User;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Xua01101ResponseDto> listUser(Map param) {

        List<User> list = userMapper.findListUser(param);
        Xua01101ResponseDto xua01101ResponseDto = new Xua01101ResponseDto();
        BeanUtils.copyProperties(list.get(0),xua01101ResponseDto);
        List<Xua01101ResponseDto> result= new ArrayList<Xua01101ResponseDto>();
        result.add(xua01101ResponseDto);
        return result;
    }

    @KafkaListener(topics = {"aaaa"})
    public void listen(ConsumerRecord record){
        System.out.println(record.topic()+":"+record.value());
    }


}
