package com.mozi.stock.exception;


import com.mozi.stock.enums.Code;

/**
 * @author zby
 * @created 2024-06-21 15:20
 * @description 异常抛出工具类
 */
public class Exceptions {

  /**
   * 抛出自定义异常
   *
   * @param code
   */
  public static void cast(Code code) {
    throw new StockException(code);
  }


  private Exceptions() {
  }

}
