package com.qqhr.consumer.controller;

import com.qqhr.api.SayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SayController {

    @Autowired
    private SayService sayService;
    @GetMapping("/sayHello")
    public String sayHello(String str){
        System.out.println("return str");
        String result = sayService.sayHello(str);
        return result;
    }
}
