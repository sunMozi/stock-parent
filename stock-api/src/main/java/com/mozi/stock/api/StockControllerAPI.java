package com.mozi.stock.api;


import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.response.PageResult;
import com.mozi.stock.response.ResponseResult;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author moZiA
 * @date 2025/6/10 19:57
 * @description
 */
@RequestMapping(path = "/api/quot")
public interface StockControllerAPI {

  @GetMapping("/stock/updown")
  ResponseResult<StockUpDownVO<InfoVO>> stockUpdown();


  @GetMapping("/stock/tradevol")
  ResponseResult<TradeAmtVO<OptionVO>> tradeAmt();


  @GetMapping("/stock/export")
  void export(@RequestParam("page") Integer page,
              @RequestParam("pageSize") Integer pageSize,
              HttpServletResponse response) throws IOException;

  @GetMapping("/stock/updown/count")
  ResponseResult<UpDownVO<OptionVO>> updown();


  @GetMapping("/stock/all")
  ResponseResult<PageResult<MoreVO>> more(@RequestParam("page") Integer page,
                                          @RequestParam("pageSize") Integer pageSize);

  @GetMapping("/stock/increase")
  ResponseResult<List<IncreaseVO>> stockIncrease();

  @GetMapping("/stock/business/all")
  ResponseResult<List<StockBusiness>> getStockBusiness();

  @GetMapping("/index/all")
  ResponseResult<List<InnerMarketVO>> innerIndexAll();

  @GetMapping("/sector/all")
  ResponseResult<List<SectorAllVO>> sectorAll();


}