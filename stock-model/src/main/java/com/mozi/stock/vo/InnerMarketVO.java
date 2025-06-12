package com.mozi.stock.vo;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moZiA
 * @date 2025/6/12 13:49
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InnerMarketVO {

  /**
   * 交易量
   */
  private Long tradeAmt;
  /**
   * 前收盘价
   */
  private BigDecimal preClosePrice;
  /**
   * 大盘编码
   */
  private String code;
  /**
   * 大盘名称
   */
  private String name;
  /**
   * 当前日期
   */
  private String curDate;
  /**
   * 开盘价
   */
  private BigDecimal openPrice;
  /**
   * 交易金额
   */
  private Long tradeVol;
  /**
   * 涨幅
   */
  private BigDecimal upDown;
  /**
   * 当前价格
   */
  private BigDecimal tradePrice;


}
