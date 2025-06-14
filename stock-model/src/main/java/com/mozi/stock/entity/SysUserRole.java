package com.mozi.stock.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户角色表
 * @TableName sys_user_role
 */
@Data
public class SysUserRole {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}