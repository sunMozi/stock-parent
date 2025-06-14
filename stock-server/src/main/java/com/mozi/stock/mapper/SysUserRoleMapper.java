package com.mozi.stock.mapper;

import com.mozi.stock.entity.SysUserRole;

/**
* @author moZiA
* @description 针对表【sys_user_role(用户角色表)】的数据库操作Mapper
* @createDate 2025-06-10 19:47:33
* @Entity com.mozi.stock.entity.SysUserRole
*/
public interface SysUserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

}
