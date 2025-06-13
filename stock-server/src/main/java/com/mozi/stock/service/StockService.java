package com.mozi.stock.service;


import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.vo.InnerMarketVO;
import com.mozi.stock.vo.SectorAllVO;
import java.util.List;

/**
 * @author moZiA
 * @date 2025/6/10 20:03
 * @description
 */
public interface StockService {

  List<StockBusiness> getStockBusiness();

  List<InnerMarketVO> innerIndexAll();

  List<SectorAllVO> sectorAll();
}