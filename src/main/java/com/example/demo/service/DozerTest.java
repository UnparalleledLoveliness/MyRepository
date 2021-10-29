package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.bean.UserVO;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DozerTest {
  public static void main(String[] args) {
    Mapper mapper = new DozerBeanMapper();
    User user = new User();
    user.setId(10005);
    user.setName("DozerTest");
    UserVO userVO = mapper.map(user, UserVO.class);
    System.out.println(userVO.toString());
  }
}
