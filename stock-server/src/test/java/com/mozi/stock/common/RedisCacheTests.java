package com.mozi.stock.common;


import com.mozi.stock.cache.impl.RedisCache;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author moZiA
 * @date 2025/6/11 8:46
 * @description
 */
@SpringBootTest
public class RedisCacheTests {

  @Resource
  private RedisCache redisCache;

  @Test
  public void testSet(){
    redisCache.set("test", "test");
  }

}