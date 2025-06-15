package com.mozi.stock.mapper;

import com.mozi.stock.entity.StockRtInfo;
import com.mozi.stock.vo.MoreVO;
import com.mozi.stock.vo.OptionVO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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

  List<MoreVO> selectMore(LocalDateTime last);

  List<OptionVO> selectUpOrDown(@Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime,
                                @Param("increaseThreshold") Double increaseThreshold);

}
