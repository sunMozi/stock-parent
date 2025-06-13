package com.mozi.stock.service.impl;


import com.mozi.stock.entity.StockBlockRtInfo;
import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.entity.StockMarketIndexInfo;
import com.mozi.stock.entity.StockMarketLogPrice;
import com.mozi.stock.mapper.StockBlockRtInfoMapper;
import com.mozi.stock.mapper.StockBusinessMapper;
import com.mozi.stock.mapper.StockMarketIndexInfoMapper;
import com.mozi.stock.mapper.StockMarketLogPriceMapper;
import com.mozi.stock.properties.MarketProperties;
import com.mozi.stock.service.StockService;
import com.mozi.stock.util.DateTimeUtil;
import com.mozi.stock.vo.InnerMarketVO;
import com.mozi.stock.vo.SectorAllVO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/6/10 20:03
 * @description
 */
@Service
public class StockServiceImpl implements StockService {


  private final StockBusinessMapper stockBusinessMapper;
  private final MarketProperties marketProperties;
  private final StockMarketIndexInfoMapper stockMarketIndexInfoMapper;
  private final StockMarketLogPriceMapper stockMarketLogPriceMapper;
  private final StockBlockRtInfoMapper stockBlockRtInfoMapper;

  public StockServiceImpl(StockBusinessMapper stockBusinessMapper,
                          MarketProperties marketProperties,
                          StockMarketIndexInfoMapper stockMarketIndexInfoMapper,
                          StockMarketLogPriceMapper stockMarketLogPriceMapper,
                          StockBlockRtInfoMapper stockBlockRtInfoMapper) {
    this.stockBusinessMapper = stockBusinessMapper;
    this.marketProperties = marketProperties;
    this.stockMarketIndexInfoMapper = stockMarketIndexInfoMapper;
    this.stockMarketLogPriceMapper = stockMarketLogPriceMapper;
    this.stockBlockRtInfoMapper = stockBlockRtInfoMapper;
  }

  @Override
  public List<StockBusiness> getStockBusiness() {
    return stockBusinessMapper.selectAll();
  }

  @Override
  public List<InnerMarketVO> innerIndexAll() {
    List<String> inner = marketProperties.getInner();

    // 实际时间
    LocalDateTime last = DateTimeUtil.getLastDateTime4Stock(LocalDateTime.now());

    // mock data TODO 模拟数据
    String mockDate = "20211226105600";
    last = LocalDateTime.parse(mockDate, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

    List<StockMarketIndexInfo> stockMarketIndexInfoList = stockMarketIndexInfoMapper.selectByInnerAndTime(
        inner,
        last);
    List<StockMarketLogPrice> stockMarketLogPrices = stockMarketLogPriceMapper.selectByInnerAndDate(
        inner,
        last.toLocalDate());

    return stockMarketIndexInfoList.stream().map((info) -> {
      InnerMarketVO innerMarketVO = InnerMarketVO.builder()
                                                 .code(info.getMarkId())
                                                 .name(info.getMarkName())
                                                 .curDate(info.getCurTime()
                                                              .toLocalDate()
                                                              .toString())
                                                 .tradeAmt(info.getTradeAccount())
                                                 .tradePrice(info.getCurrentPrice())
                                                 .tradeVol(info.getTradeVolume())
                                                 .upDown(info.getUpdownRate())
                                                 .build();
      stockMarketLogPrices.forEach(logPrice -> {
        if (logPrice.getMarketCode().equals(info.getMarkId())) {
          innerMarketVO.setOpenPrice(logPrice.getOpenPrice());
          innerMarketVO.setPreClosePrice(logPrice.getPreClosePrice());
        } else {
          innerMarketVO.setOpenPrice(BigDecimal.ZERO);
          innerMarketVO.setPreClosePrice(BigDecimal.ZERO);
        }
      });

      return innerMarketVO;
    }).toList();
  }

  @Override
  public List<SectorAllVO> sectorAll() {
    LocalDateTime last = DateTimeUtil.getLastDateTime4Stock(LocalDateTime.now());

    // 模拟数据
    last = LocalDateTime.parse("2021-12-21 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    LocalDateTime begin = DateTimeUtil.getOpenDate(last);
    LocalDateTime end = DateTimeUtil.getCloseDate(last);

    List<StockBlockRtInfo> stockBlockRtInfos = stockBlockRtInfoMapper.selectByDateTimeDesc(begin,
                                                                                           end);

    return stockBlockRtInfos.stream()
                            .map((info) -> SectorAllVO.builder()
                                                      .name(info.getBlockName())
                                                      .code(info.getLabel())
                                                      .avgPrice(info.getAvgPrice())
                                                      .companyNum(info.getCompanyNum())
                                                      .updownRate(info.getUpdownRate())
                                                      .tradeVol(info.getTradeVolume())
                                                      .tradeAmt(info.getTradeAmount())
                                                      //TODO
                                                      .curDate(info.getCurTime()
                                                                   .toLocalDate()
                                                                   .toString())
                                                      .build())
                            .toList();
  }
}