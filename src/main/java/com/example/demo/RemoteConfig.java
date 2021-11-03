package com.example.demo;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class RemoteConfig implements ApplicationContextInitializer {

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    //获取当前应用的环境
    ConfigurableEnvironment ce = applicationContext.getEnvironment();
    Properties properties = new Properties();
    properties.put("spring.datasource.url", getRemoteConfigedDbUrl("spring.datasource.url"));
    PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("remote", properties);
    ce.getPropertySources().addFirst(propertiesPropertySource);
  }

  private String getRemoteConfigedDbUrl(String propertname) {
    return "jdbc:mysql://localhost:3306/test";
  }
}
