package com.mozi.stock.mapper;

import com.mozi.stock.entity.StockBusiness;
import java.util.List;

/**
 * @author moZiA
 * @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
 * @createDate 2025-06-10 19:47:33
 * @Entity com.mozi.stock.entity.StockBusiness
 */
public interface StockBusinessMapper {


  List<StockBusiness> selectAll();
}
