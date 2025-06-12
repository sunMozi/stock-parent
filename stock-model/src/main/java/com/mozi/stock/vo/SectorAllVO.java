package com.mozi.stock.vo;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/6/12 15:04
 * @description
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorAllVO {

  /** 公司数量 */
  private Integer companyNum;

  /** 成交股数 */
  private Long tradeAmt;

  /** 板块编码 */
  private String code;

  /** 平均价格 */
  private BigDecimal avgPrice;

  /** 板块名称 */
  private String name;

  /** 当前日期（格式：yyyyMMdd） */
  private String curDate;

  /** 成交总金额 */
  private Long tradeVol;

  /** 涨跌幅（单位：百分比，如0.196表示0.196%） */
  private BigDecimal updownRate;

}