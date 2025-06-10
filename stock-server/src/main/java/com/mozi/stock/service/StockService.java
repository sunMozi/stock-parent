package com.mozi.stock.service;


import com.mozi.stock.entity.StockBusiness;
import java.util.List;

/**
 * @author moZiA
 * @date 2025/6/10 20:03
 * @description
 */
public interface StockService {

  List<StockBusiness> getStockBusiness();
}