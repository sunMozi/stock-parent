package com.mozi.stock.config;


import com.mozi.stock.cache.Cache;
import com.mozi.stock.cache.impl.RedisCache;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author moZiA
 * @date 2025/6/11 11:36
 * @description
 */
@Configurable
public class RedisConfiguration {

  @Bean
  public Cache cache(StringRedisTemplate stringRedisTemplate) {

    return new RedisCache(stringRedisTemplate);

  }

}