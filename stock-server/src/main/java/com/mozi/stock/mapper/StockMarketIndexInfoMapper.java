package com.mozi.stock.mapper;

import com.mozi.stock.entity.StockMarketIndexInfo;
import com.mozi.stock.vo.OptionVO;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author moZiA
 * @description 针对表【stock_market_index_info(股票大盘数据详情表)】的数据库操作Mapper
 * @createDate 2025-06-10 19:47:33
 * @Entity com.mozi.stock.entity.StockMarketIndexInfo
 */
public interface StockMarketIndexInfoMapper {

  int deleteByPrimaryKey(Long id);

  int insert(StockMarketIndexInfo record);

  int insertSelective(StockMarketIndexInfo record);

  StockMarketIndexInfo selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(StockMarketIndexInfo record);

  int updateByPrimaryKey(StockMarketIndexInfo record);


  List<StockMarketIndexInfo> selectByInnerAndTime(@Param("inner") List<String> inner,
                                                  @Param("last") LocalDateTime last);

  List<OptionVO> selectTradeCount(@Param("b") LocalDateTime t1Open,
                                  @Param("e") LocalDateTime tClose,
                                  @Param("inner") List<String> inner);

  void insertBatch(List<StockMarketIndexInfo> stockMarketIndexInfoList);
}
