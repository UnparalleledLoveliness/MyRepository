package com.example.demo.controller;

import com.example.demo.annotation.My;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloControl {
    @My
    @ResponseBody
    @RequestMapping("/hello")
    public String hello()
    {
        return "hello spring boot";
    }

    @Autowired
    RedisTemplate redisTemplate;

}
