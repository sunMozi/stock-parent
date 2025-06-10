package com.mozi.stock.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 角色表
 * @TableName sys_role
 */
@Data
public class SysRole {
    /**
     * 主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态(1:正常0:弃用)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除(1未删除；0已删除)
     */
    private Integer deleted;
}