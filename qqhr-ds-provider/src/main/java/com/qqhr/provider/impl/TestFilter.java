package com.qqhr.provider.impl;

import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.platfrom.filter.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WilliamDragon
 * @Date 2021/4/16 13:41
 * @Version 1.0
 */
@RestController
public class TestFilter {

    @Autowired
    private FilterChain filterChain;
    @PostMapping("/testFilterChain")
    public void testFilterChain(){
        AsyncMessage asyncMessage = new AsyncMessage();
        this.filterChain.filter(asyncMessage);

        System.out.println("执行成功");
    }

}
