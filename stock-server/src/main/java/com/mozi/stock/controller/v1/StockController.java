package com.mozi.stock.controller.v1;


import com.mozi.stock.api.StockControllerAPI;
import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.response.ResponseResult;
import com.mozi.stock.service.StockService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/6/10 20:02
 * @description
 */
@RestController
public class StockController implements StockControllerAPI {

  @Resource
  private StockService stockService;

  @Override
  public ResponseResult<List<StockBusiness>> getStockBusiness() {
    return ResponseResult.ok(stockService.getStockBusiness());

  }
}