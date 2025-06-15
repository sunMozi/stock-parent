package com.mozi.stock.constant;


/**
 * @author moZiA
 * @date 2025/6/11 11:42
 * @description
 */
public class Constants {

  /**
   * 数值常量：4
   * 用于通用占位或需要固定数字4的业务逻辑中
   */
  public static final Integer FOUR = 4;

  /**
   * 图形验证码 Redis 缓存前缀
   * 用于构造完整缓存 key，例如：stock:captcha:sessionId
   */
  public static final String CAPTCHA_PREFIX = "stock:captcha";

  /**
   * 图形验证码过期时间（单位：秒）
   * 用于限制验证码有效期，默认 60 秒，超时需重新获取
   */
  public static final Long CAPTCHA_EXPIRE_TIME = 60L;

  /**
   * 系统用户状态：正常（1）
   */
  public static final Integer SYS_USER_STATUS_NORMAL = 1;

  /**
   * 系统用户状态：锁定（2）
   */
  public static final Integer SYS_USER_STATUS_LOCKED = 2;

  /**
   * 用户删除状态：已删除（0）
   */
  public static final Integer SYS_USER_DELETED = 0;

  /**
   * 用户删除状态：未删除（1）
   */
  public static final Integer SYS_USER_UN_DELETED = 1;

  /**
   * 股票实时数据导出文件名前缀
   */
  public static final String EXCEL_FILE_PREFIX = "stock-rt-info-";

  /**
   * 股票实时数据导出文件名后缀
   */
  public static final String EXCEL_FILE_SUFFIX = ".xlsx";
}