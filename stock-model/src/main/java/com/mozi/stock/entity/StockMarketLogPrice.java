package com.mozi.stock.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * 股票大盘 开盘价与前收盘价流水表
 * @TableName stock_market_log_price
 */
@Data
public class StockMarketLogPrice {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 大盘编码
     */
    private String marketCode;

    /**
     * 当前日期
     */
    private LocalDate curDate;

    /**
     * 前收盘价格
     */
    private BigDecimal preClosePrice;

    /**
     * 开盘价格
     */
    private BigDecimal openPrice;
}