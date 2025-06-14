package com.mozi.stock.mapper;

import com.mozi.stock.entity.StockBlockRtInfo;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author moZiA
 * @description 针对表【stock_block_rt_info(股票板块详情信息表)】的数据库操作Mapper
 * @createDate 2025-06-10 19:47:33
 * @Entity com.mozi.stock.entity.StockBlockRtInfo
 */
public interface StockBlockRtInfoMapper {

  int deleteByPrimaryKey(Long id);

  int insert(StockBlockRtInfo record);

  int insertSelective(StockBlockRtInfo record);

  StockBlockRtInfo selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(StockBlockRtInfo record);

  int updateByPrimaryKey(StockBlockRtInfo record);

  List<StockBlockRtInfo> selectByDateTimeDesc(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
}
