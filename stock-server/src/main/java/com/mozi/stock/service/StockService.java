package com.mozi.stock.service;


import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.response.PageResult;
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

/**
 * @author moZiA
 * @date 2025/6/10 20:03
 * @description
 */
public interface StockService {

  List<StockBusiness> getStockBusiness();

  List<InnerMarketVO> innerIndexAll();

  List<SectorAllVO> sectorAll();

  List<IncreaseVO> stockIncrease();

  PageResult<MoreVO> more(Integer page, Integer pageSize);

  UpDownVO<OptionVO> updown();

  void export(Integer page, Integer pageSize, HttpServletResponse response) throws IOException;

  TradeAmtVO<OptionVO> tradeAmt();

  StockUpDownVO<InfoVO> stockUpdown();
}