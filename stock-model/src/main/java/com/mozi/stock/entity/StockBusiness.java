package com.mozi.stock.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 主营业务表
 * @TableName stock_business
 */
@Data
public class StockBusiness {
    /**
     *  股票编码
     */
    private String secCode;

    /**
     * 股票名称
     */
    private String secName;

    /**
     * 行业板块代码
     */
    private String sectorCode;

    /**
     * 行业板块名称
     */
    private String sectorName;

    /**
     * 主营业务
     */
    private String business;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}