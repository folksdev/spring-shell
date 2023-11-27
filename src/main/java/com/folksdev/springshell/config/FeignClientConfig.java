package com.folksdev.springshell.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

  @Bean
  RequestInterceptor requestInterceptor(@Value("${api.key}") String apiKey) {
    return requestTemplate -> requestTemplate.header("authorization", apiKey);
  }
}
