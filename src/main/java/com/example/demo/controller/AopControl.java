package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class AopControl {

    @GetMapping("/aopTest")
    public JSONObject aopTest()
    {
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }
}
