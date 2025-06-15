package com.mozi.stock.controller.v1;


import com.mozi.stock.api.StockControllerAPI;
import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.response.PageResult;
import com.mozi.stock.response.ResponseResult;
import com.mozi.stock.service.StockService;
import com.mozi.stock.vo.IncreaseVO;
import com.mozi.stock.vo.InnerMarketVO;
import com.mozi.stock.vo.MoreVO;
import com.mozi.stock.vo.SectorAllVO;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moZiA
 * @date 2025/6/10 20:02
 * @description
 */
@RestController
public class StockController implements StockControllerAPI {

  private final StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @Override
  public ResponseResult<PageResult<MoreVO>> more(Integer page, Integer pageSize) {
    return ResponseResult.ok(stockService.more(page, pageSize));
  }

  @Override
  public ResponseResult<List<IncreaseVO>> stockIncrease() {
    return ResponseResult.ok(stockService.stockIncrease());
  }

  @Override
  public ResponseResult<List<StockBusiness>> getStockBusiness() {
    return ResponseResult.ok(stockService.getStockBusiness());

  }

  @Override
  public ResponseResult<List<InnerMarketVO>> innerIndexAll() {
    return ResponseResult.ok(stockService.innerIndexAll());
  }

  @Override
  public ResponseResult<List<SectorAllVO>> sectorAll() {
    return ResponseResult.ok(stockService.sectorAll());
  }
}