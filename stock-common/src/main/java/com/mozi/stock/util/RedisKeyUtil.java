package com.mozi.stock.util;


/**
 * @author moZiA
 * @date 2025/6/11 11:40
 * @description
 */
public class RedisKeyUtil {

  public static String generateKey(String prefix, String bizKey) {
    return prefix + ":" + bizKey;
  }


  private RedisKeyUtil() {
  }

}