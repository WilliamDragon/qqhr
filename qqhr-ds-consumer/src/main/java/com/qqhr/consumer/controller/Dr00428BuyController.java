package com.qqhr.consumer.controller;

import com.qqhr.api.Dr000428BuyService;
import com.qqhr.api.Dr000428ChangeBuyService;
import com.qqhr.common.utils.R;
import com.qqhr.dto.Dr000428RequestDto;
import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.golden.trade.async.AsyncMessageBuilder;
import com.qqhr.platfrom.service.KafkaSendTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author WilliamDragon
 * @Date 2021/5/21 14:28
 * @Version 1.0
 */
@RestController
public class Dr00428BuyController {

    @Autowired
    private Dr000428ChangeBuyService dr000428ChangeBuyService;

    @PostMapping("/Dr00428Buy")
    public R Dr00428Buy(Dr000428RequestDto dr00428RequestDto) throws IllegalAccessException, ClassNotFoundException {
        HashMap<String, String> queryParam = new HashMap<>();

        R result = dr000428ChangeBuyService.execute(dr00428RequestDto);
        return R.ok();

    }
}
