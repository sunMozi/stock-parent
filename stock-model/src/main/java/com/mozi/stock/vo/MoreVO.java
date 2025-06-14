package com.mozi.stock.vo;


import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/6/14 20:09
 * @description
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoreVO {
  /**
   * 股票编码（如 000004）
   */
  private String code;

  /**
   * 股票名称（如 国华网安）
   */
  private String name;
  /**
   * 交易量（单位：股）
   */
  private Long tradeVol;

  /**
   * 当前日期（格式：yyyyMMdd）
   */
  private LocalDate curDate;

  /**
   * 当前价格（元）
   */
  private BigDecimal tradePrice;

  /**
   * 前收盘价（元）
   */
  private BigDecimal preClosePrice;

  /**
   * 涨跌幅（0.039936 表示 3.99%）
   */
  private BigDecimal increase;

  /**
   * 涨跌额（单位：元）
   */
  private BigDecimal upDown;

  /**
   * 振幅（如 0.059638 表示 5.96%）
   */
  private BigDecimal amplitude;

  /**
   * 交易金额（单位：元）
   */
  private BigDecimal tradeAmt;

}