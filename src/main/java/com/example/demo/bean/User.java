package com.example.demo.bean;
import org.apache.ibatis.annotations.Mapper;
import org.dozer.Mapping;

import lombok.Data;

@Data
public class User {
    @Mapping("longid")
    private long id;
    @Mapping("id")
    private String name;
}
