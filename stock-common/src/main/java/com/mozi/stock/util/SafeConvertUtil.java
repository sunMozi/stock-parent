package com.mozi.stock.util;

import java.math.BigDecimal;

/**
 * 安全类型转换工具类，提供基础类型的健壮转换能力。
 */
public class SafeConvertUtil {

  private SafeConvertUtil() {
    // 禁止实例化
    throw new UnsupportedOperationException(
        "SafeConvertUtil is a utility class and cannot be instantiated");
  }

  /**
   * 安全转换字符串为 Long 类型，转换失败返回 0。
   */
  public static Long toLong(String value) {
    return toLong(value, 0L);
  }

  /**
   * 安全转换字符串为 Long 类型，支持自定义默认值。
   */
  public static Long toLong(String value, Long defaultValue) {
    try {
      return Long.parseLong(value.trim());
    } catch (Exception ex) {
      return defaultValue;
    }
  }

  /**
   * 安全转换字符串为 BigDecimal 类型，转换失败返回 BigDecimal.ZERO。
   */
  public static BigDecimal toBigDecimal(String value) {
    return toBigDecimal(value, BigDecimal.ZERO);
  }

  /**
   * 安全转换字符串为 BigDecimal 类型，支持自定义默认值。
   */
  public static BigDecimal toBigDecimal(String value, BigDecimal defaultValue) {
    try {
      return new BigDecimal(value.trim());
    } catch (Exception ex) {
      return defaultValue;
    }
  }
}
