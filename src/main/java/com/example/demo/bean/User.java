package com.example.demo.bean;

import org.dozer.Mapping;

import lombok.Data;

@Data
public class User {
    @Mapping("longid")
    private long id;
    private String name;
    private String userName;
    private String password;
}
