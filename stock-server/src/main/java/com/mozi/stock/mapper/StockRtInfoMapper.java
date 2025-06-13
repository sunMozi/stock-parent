package com.mozi.stock.mapper;

import com.mozi.stock.entity.StockBlockRtInfo;
import com.mozi.stock.entity.StockRtInfo;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author moZiA
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2025-06-10 19:47:33
* @Entity com.mozi.stock.entity.StockRtInfo
*/
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

    List<StockRtInfo> selectByDateTime(LocalDateTime last);
}
