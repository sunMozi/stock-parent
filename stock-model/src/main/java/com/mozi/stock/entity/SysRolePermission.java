package com.mozi.stock.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 角色权限表
 * @TableName sys_role_permission
 */
@Data
public class SysRolePermission {
    /**
     * 主键
     */
    private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单权限id
     */
    private String permissionId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}