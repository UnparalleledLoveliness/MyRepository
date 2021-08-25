package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.City;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.CityService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class IndexCntroller {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CityService cityService;


    @ResponseBody
    @PostMapping("/city")
    public String getCityById(@RequestParam("id") long id) throws JSONException {

        System.out.println(id);
        JSONObject result = new JSONObject();
        City c=cityService.getById(id);
        if(c!=null) {
            result.put("status", "success");
            result.put("id", id);
        }
        else
        { result.put("status", "fail");
        }
        System.out.println(JSON.toJSONString(c));
        System.out.println(result.toString());

       return result.toString();

    }

    @ResponseBody
    @GetMapping("/incity")
    public void inserCity(@RequestParam("country") String country,@RequestParam("name") String name)
    {
        City c=new City();
        c.setCountry(country);
        c.setName(name);
         cityService.insert(c);
    }

    @ResponseBody
    @GetMapping("/acct")
    public User getById(@RequestParam("id") Long id)
    {
        return userMapper.getUser(id);
    }

    @RequestMapping("/test")
    public String test()
    {
        return "test.html";
    }


}
