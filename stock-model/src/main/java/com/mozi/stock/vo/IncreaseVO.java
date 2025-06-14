package com.mozi.stock.vo;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/6/13 8:32
 * @description
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseVO {
  /**
   * 交易量
   */
  private Long tradeAmt;
  /**
   * 前收盘价
   */
  private BigDecimal preClosePrice;
  /**
   * 振幅
   */
  private BigDecimal amplitude;
  /**
   * 股票编码
   */
  private String code;
  /**
   * 名称
   */
  private String name;
  /**
   * 日期
   */
  private String curDate;
  /**
   * 交易金额
   */
  private BigDecimal tradeVol;
  /**
   * 张涨跌
   */
  private BigDecimal increase;

  /**
   * 涨幅
   */
  private BigDecimal upDown;
  /**
   * 当前价格
   */
  private BigDecimal tradePrice;
}