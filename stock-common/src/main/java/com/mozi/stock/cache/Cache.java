package com.mozi.stock.cache;

/**
 * @author MoZi
 * @createTime 2025/6/11 8:35
 */

public interface Cache {

  /**
   * 写入缓存（带过期时间）。
   *
   * @param key     缓存键，必须唯一，用于标识缓存条目，建议使用命名空间前缀，例如："user:token:123"
   * @param value   缓存值，建议为 JSON 字符串或序列化对象
   * @param timeout 超时时间（单位：秒），超时后缓存将自动失效
   */
  void set(String key, String value, long timeout);

  /**
   * 写入缓存（无过期时间）。
   *
   * <p>该方法写入的值将永久存在，除非主动删除，建议仅用于静态数据。</p>
   *
   * @param key   缓存键
   * @param value 缓存值
   */
  void set(String key, String value);

  /**
   * 读取缓存。
   *
   * @param key 缓存键
   * @return 缓存值，若 key 不存在或已过期，则返回 null
   */
  String get(String key);

  /**
   * 删除缓存。
   *
   * @param key 缓存键
   */
  void delete(String key);

}
