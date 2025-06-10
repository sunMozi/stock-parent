package com.mozi.stock.mapper;

import com.mozi.stock.entity.SysRolePermission;

/**
* @author moZiA
* @description 针对表【sys_role_permission(角色权限表)】的数据库操作Mapper
* @createDate 2025-06-10 19:47:33
* @Entity com.mozi.stock.entity.SysRolePermission
*/
public interface SysRolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

}
