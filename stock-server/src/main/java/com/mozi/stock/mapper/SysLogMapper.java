package com.mozi.stock.mapper;

import com.mozi.stock.entity.SysLog;

/**
* @author moZiA
* @description 针对表【sys_log(系统日志)】的数据库操作Mapper
* @createDate 2025-06-10 19:47:33
* @Entity com.mozi.stock.entity.SysLog
*/
public interface SysLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

}
