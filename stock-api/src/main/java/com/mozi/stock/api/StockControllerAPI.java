package com.mozi.stock.api;


import com.mozi.stock.entity.StockBlockRtInfo;
import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.response.ResponseResult;
import java.util.List;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author moZiA
 * @date 2025/6/10 19:57
 * @description
 */
@RequestMapping(path = "/api/quot")
public interface StockControllerAPI {

  @GetMapping("/stock/business/all")
  ResponseResult<List<StockBusiness>> getStockBusiness();

}