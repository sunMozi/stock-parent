package com.mozi.stock.constant;


/**
 * @author moZiA
 * @date 2025/6/11 11:42
 * @description
 */
public class Constants {

  //  数字4
  public static final Integer FOUR = 4;

  //  验证码前缀
  public static final String CAPTCHA_PREFIX = "stock:captcha";
  // 验证码过期时间
  public static final Long CAPTCHA_EXPIRE_TIME = 60L;

  // 用户状态 1: 正常
  public static final Integer SYS_USER_STATUS_NORMAL = 1;
  // 用户被锁 2: 锁定
  public static final Integer SYS_USER_STATUS_LOCKED = 2;
  // 删除状态 0: 删除
  public static final Integer SYS_USER_DELETED = 0;
  // 未删除状态 1: 未删除
  public static final Integer SYS_USER_UN_DELETED = 1;
}