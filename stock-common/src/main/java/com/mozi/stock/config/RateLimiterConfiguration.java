package com.mozi.stock.config;


import com.google.common.util.concurrent.RateLimiter;
import com.mozi.stock.properties.LimiterProperties;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author moZiA
 * @date 2025/6/11 11:36
 * @description
 */
@Configuration
@SuppressWarnings("all")
public class RateLimiterConfiguration {
  @Bean
  public RateLimiter globalRateLimiter(LimiterProperties limiterProperties) {
    return RateLimiter.create(limiterProperties.getConcurrentNum());
  }

}
