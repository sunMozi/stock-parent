package com.mozi.stock.cache.impl;


import com.mozi.stock.cache.Cache;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author moZiA
 * @date 2025/6/11 8:39
 * @description
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Redis 缓存实现，基于 Spring 的 StringRedisTemplate 封装。
 * <p>
 * 支持基础 KV 操作，常用于会话管理、配置缓存、短期状态存储等场景。
 */
@Slf4j
@Component
public class RedisCache implements Cache {

  private final StringRedisTemplate redisTemplate;

  public RedisCache(StringRedisTemplate redisTemplate) {
    this.redisTemplate = Objects.requireNonNull(redisTemplate, "RedisTemplate must not be null");
  }

  @Override
  public void set(String key, String value, long timeout) {
    try {
      redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
      log.debug("[RedisCache] SET key={} (TTL={}s)", key, timeout);
    } catch (Exception e) {
      log.error("[RedisCache] SET with timeout failed: key={}, error={}", key, e.getMessage(), e);
      throw new RuntimeException("Redis 缓存写入失败", e);
    }
  }

  @Override
  public void set(String key, String value) {
    try {
      redisTemplate.opsForValue().set(key, value);
      log.debug("[RedisCache] SET key={} (no TTL)", key);
    } catch (Exception e) {
      log.error("[RedisCache] SET failed: key={}, error={}", key, e.getMessage(), e);
      throw new RuntimeException("Redis 缓存写入失败", e);
    }
  }

  @Override
  public String get(String key) {
    try {
      String value = redisTemplate.opsForValue().get(key);
      log.debug("[RedisCache] GET key={} => {}", key, value);
      return value;
    } catch (Exception e) {
      log.error("[RedisCache] GET failed: key={}, error={}", key, e.getMessage(), e);
      throw new RuntimeException("Redis 缓存读取失败", e);
    }
  }

  @Override
  public void delete(String key) {
    try {
      Boolean deleted = redisTemplate.delete(key);
      log.debug("[RedisCache] DELETE key={} => {}", key, deleted);
    } catch (Exception e) {
      log.error("[RedisCache] DELETE failed: key={}, error={}", key, e.getMessage(), e);
      throw new RuntimeException("Redis 缓存删除失败", e);
    }
  }
}
