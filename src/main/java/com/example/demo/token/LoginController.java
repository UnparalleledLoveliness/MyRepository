package com.example.demo.token;

import com.example.demo.bean.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @PostMapping("/login")
  @ResponseBody
  public String login(String username, String password) {
    User user = new User();
    user.setName(username);
    user.setPassword(password);
    String token = TokenUtil.sign(user);
    return token;
  }
}
