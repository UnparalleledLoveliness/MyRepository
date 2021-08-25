package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
@Slf4j
@SpringBootTest
class DemoApplicationTests {


    @Autowired
     JdbcTemplate jdbcTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        //jdbcTemplate.queryForObject("select * from user ");
      Long aLong=  jdbcTemplate.queryForObject("select COUNT(*) from user",Long.class);
        log.info("成功:{}",aLong);
    }

    @Test
    void testRedis()
    {
        ValueOperations<String,String> operations=redisTemplate.opsForValue();
        operations.set("你好","世界");
        System.out.println(operations.get("你好"));
    }

}
