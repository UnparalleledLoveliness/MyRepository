package com.example.demo.controller;

import com.example.demo.service.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class RdeisControl {
    @Autowired
    RedisUtils redisUtils;

    @ResponseBody
    @GetMapping("/redis")
    public String redis(@RequestParam("id") String id)
    {
        System.out.println(id);
        if(redisUtils.exists(id))
        {
            Object object=redisUtils.get(id);
            log.info("从缓存获取的数据"+ object);
            return object.toString();
        }
        else
        {
            redisUtils.set(id,"我自己家的",10L, TimeUnit.MINUTES);
        }

        return "不存在";
    }

}
