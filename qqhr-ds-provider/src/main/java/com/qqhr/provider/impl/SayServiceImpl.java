package com.qqhr.provider.impl;

import com.qqhr.api.SayService;
import com.qqhr.dao.mapper.UserMapper;
import com.qqhr.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
