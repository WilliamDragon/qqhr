package com.qqhr.consumer.controller;

import com.qqhr.api.SayService;
import com.qqhr.dto.Xua01101RequestDto;
import com.qqhr.dto.Xua01101ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class SayController {

    @Autowired
    private SayService sayService;
    @GetMapping("/sayHello")
    public String sayHello(String str){
        System.out.println("进入sayHello");
        String result = sayService.sayHello(str);
        return result;
    }
    @PostMapping("/findUser")
    public List<Xua01101ResponseDto> findUser(Xua01101RequestDto xua01101RequestDto){
        HashMap<String, String> queryParam = new HashMap<>();
        queryParam.put("id",xua01101RequestDto.getId().toString());
        List<Xua01101ResponseDto> users = sayService.listUser(queryParam);
        return users;

    }

    /*@Autowired
    private KafkaTemplate template;
    //软件老王，topic使用上测试创建的aaaa
    @GetMapping("/sendMsg")
    public String sendMsg(String topic, String message){
        template.send(topic,message);
        return "success";
    }*/
}
