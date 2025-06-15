package com.mozi.stock.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 股票信息导出DTO，用于 Excel 文件导出展示。
 * 该类映射股票相关的实时数据字段，包含交易量、价格、涨跌幅等核心指标。
 * 实现Serializable接口，便于序列化传输和缓存。
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockInfoExcelDTO implements Serializable {


  /**
   * 编号（序号）
   */
  @ExcelProperty(index = 0, value = "编号")
  private Integer num;

  /**
   * 股票编码（例如：000004）
   */
  @ExcelProperty(index = 1, value = "股票编码")
  private String code;

  /**
   * 股票名称（例如：国华网安）
   */
  @ExcelProperty(index = 2, value = "股票名称")
  private String name;

  /**
   * 交易量，单位：股
   */
  @ExcelProperty(index = 3, value = "交易量")
  private Long tradeVol;

  /**
   * 当前日期，格式：yyyyMMdd
   */
  @ExcelProperty(index = 4, value = "当前日期")
  private LocalDate curDate;

  /**
   * 当前价格，单位：元
   */
  @ExcelProperty(index = 5, value = "当前价格")
  private BigDecimal tradePrice;

  /**
   * 前收盘价，单位：元
   */
  @ExcelProperty(index = 6, value = "前收盘价")
  private BigDecimal preClosePrice;

  /**
   * 涨跌幅（0.039936 表示 3.99%）
   */
  @ExcelProperty(index = 7, value = "涨跌幅")
  private BigDecimal increase;

  /**
   * 涨跌额，单位：元
   */
  @ExcelProperty(index = 8, value = "涨跌额")
  private BigDecimal upDown;

  /**
   * 振幅（0.059638 表示 5.96%）
   */
  @ExcelProperty(index = 9, value = "振幅")
  private BigDecimal amplitude;

  /**
   * 交易金额，单位：元
   */
  @ExcelProperty(index = 10, value = "交易金额")
  private BigDecimal tradeAmt;

}
