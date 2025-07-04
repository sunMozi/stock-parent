package com.mozi.stock.controller.v1;


import com.mozi.stock.api.StockControllerAPI;
import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.response.PageResult;
import com.mozi.stock.response.ResponseResult;
import com.mozi.stock.service.StockService;
import com.mozi.stock.vo.IncreaseVO;
import com.mozi.stock.vo.InfoVO;
import com.mozi.stock.vo.InnerMarketVO;
import com.mozi.stock.vo.MoreVO;
import com.mozi.stock.vo.OptionVO;
import com.mozi.stock.vo.SectorAllVO;
import com.mozi.stock.vo.StockUpDownVO;
import com.mozi.stock.vo.TradeAmtVO;
import com.mozi.stock.vo.UpDownVO;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
  public ResponseResult<StockUpDownVO<InfoVO>> stockUpdown() {
    return ResponseResult.ok(stockService.stockUpdown());
  }

  @Override
  public ResponseResult<TradeAmtVO<OptionVO>> tradeAmt() {
    return ResponseResult.ok(stockService.tradeAmt());
  }

  @Override
  public void export(Integer page, Integer pageSize, HttpServletResponse response)
      throws IOException {
    stockService.export(page, pageSize, response);
  }

  @Override
  public ResponseResult<UpDownVO<OptionVO>> updown() {
    return ResponseResult.ok(stockService.updown());
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