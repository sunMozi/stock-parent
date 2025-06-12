package com.mozi.stock.service.impl;


import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.mapper.StockBusinessMapper;
import com.mozi.stock.service.StockService;
import com.mozi.stock.vo.InnerMarketVO;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/6/10 20:03
 * @description
 */
@Service
public class StockServiceImpl implements StockService {


  @Resource
  private StockBusinessMapper stockBusinessMapper;

  @Override
  public List<StockBusiness> getStockBusiness() {
    return stockBusinessMapper.selectAll();
  }

  @Override
  public List<InnerMarketVO> innerIndexAll() {
    return null;
  }
}