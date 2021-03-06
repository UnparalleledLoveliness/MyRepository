package com.example.demo.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

//@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Autowired
  private TokenInterceptor tokenInterceptor;

  /**
   * 配置拦截器、拦截路径
   * 每次请求到拦截的路径，就会去执行拦截器中的方法
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    List<String> excludePath = new ArrayList<>();
    //排除拦截，除了注册登录(此时还没token)，其他都拦截
    excludePath.add("/register");  //登录
    excludePath.add("/login");     //注册'
    excludePath.add("/hello");
    excludePath.add("/static/**");  //静态资源
    excludePath.add("/assets/**");  //静态资源
    registry.addInterceptor(tokenInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(excludePath);
    WebMvcConfigurer.super.addInterceptors(registry);
  }
}
