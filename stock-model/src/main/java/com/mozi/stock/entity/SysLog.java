package com.mozi.stock.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 系统日志
 * @TableName sys_log
 */
@Data
public class SysLog {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 响应时间
     */
    private Integer time;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}