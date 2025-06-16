package com.mozi.stock.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mozi.stock.constant.Constants;
import com.mozi.stock.dto.StockInfoExcelDTO;
import com.mozi.stock.entity.StockBlockRtInfo;
import com.mozi.stock.entity.StockBusiness;
import com.mozi.stock.entity.StockMarketIndexInfo;
import com.mozi.stock.entity.StockMarketLogPrice;
import com.mozi.stock.entity.StockRtInfo;
import com.mozi.stock.handler.CustomSheetWriteHandler;
import com.mozi.stock.mapper.StockBlockRtInfoMapper;
import com.mozi.stock.mapper.StockBusinessMapper;
import com.mozi.stock.mapper.StockMarketIndexInfoMapper;
import com.mozi.stock.mapper.StockMarketLogPriceMapper;
import com.mozi.stock.mapper.StockRtInfoMapper;
import com.mozi.stock.properties.MarketProperties;
import com.mozi.stock.properties.ThresholdProperties;
import com.mozi.stock.response.PageResult;
import com.mozi.stock.service.StockService;
import com.mozi.stock.util.DateTimeUtil;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/6/10 20:03
 * @description
 */
@Service
public class StockServiceImpl implements StockService {


  private final MarketProperties marketProperties;
  private final ThresholdProperties thresholdProperties;
  private final StockBusinessMapper stockBusinessMapper;
  private final StockMarketIndexInfoMapper stockMarketIndexInfoMapper;
  private final StockMarketLogPriceMapper stockMarketLogPriceMapper;
  private final StockBlockRtInfoMapper stockBlockRtInfoMapper;
  private final StockRtInfoMapper stockRtInfoMapper;

  public StockServiceImpl(StockBusinessMapper stockBusinessMapper,
                          MarketProperties marketProperties,
                          ThresholdProperties thresholdProperties,
                          StockMarketIndexInfoMapper stockMarketIndexInfoMapper,
                          StockMarketLogPriceMapper stockMarketLogPriceMapper,
                          StockBlockRtInfoMapper stockBlockRtInfoMapper,
                          StockRtInfoMapper stockRtInfoMapper) {
    this.stockBusinessMapper = stockBusinessMapper;
    this.marketProperties = marketProperties;
    this.thresholdProperties = thresholdProperties;
    this.stockMarketIndexInfoMapper = stockMarketIndexInfoMapper;
    this.stockMarketLogPriceMapper = stockMarketLogPriceMapper;
    this.stockBlockRtInfoMapper = stockBlockRtInfoMapper;
    this.stockRtInfoMapper = stockRtInfoMapper;
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
    last = LocalDateTime.parse("2021-12-21 00:00:00",
                               DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

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

  @Override
  public List<IncreaseVO> stockIncrease() {

    LocalDateTime last = DateTimeUtil.getLastDateTime4Stock(LocalDateTime.now());

    // TODO MOCK 数据
    last = LocalDateTime.parse("2021-12-30 09:32:00",
                               DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    // TODO MOCK 数据

    List<StockRtInfo> stockBlockRtInfos = stockRtInfoMapper.selectByDateTime(last);

    //涨跌：当前价-前收盘价
    //涨幅：（当前价-前收盘价）/前收盘价 * 100%
    //振幅：（最高成交价-最低成交价）/前收盘价 * 100%

    List<IncreaseVO> increaseVOS = new ArrayList<>();
    stockBlockRtInfos.forEach(info -> {
      IncreaseVO increaseVO = new IncreaseVO();
      increaseVO.setCode(info.getStockCode());
      increaseVO.setName(info.getStockName());
      increaseVO.setTradePrice(info.getCurPrice());
      increaseVO.setTradeAmt(info.getTradeAmount());
      increaseVO.setPreClosePrice(info.getPreClosePrice());
      increaseVO.setTradeVol(info.getTradeVolume());
      increaseVO.setCurDate(info.getCurTime()
                                .toLocalDate()
                                .format(DateTimeFormatter.ofPattern("yyyyMMdd")));

      // 涨幅计算（保留 2 位小数，四舍五入）
      BigDecimal increase = info.getCurPrice()
                                .subtract(info.getPreClosePrice())
                                .divide(info.getPreClosePrice(), 6, RoundingMode.HALF_UP);

      // 振幅计算（保留 2 位小数，直接截断，不四舍五入）
      BigDecimal amplitude = info.getMaxPrice()
                                 .subtract(info.getMinPrice())
                                 .divide(info.getPreClosePrice(),
                                         6,
                                         RoundingMode.DOWN); // 保留两位小数，向下取整（截断）

      // 涨跌
      BigDecimal unDown = info.getCurPrice().subtract(info.getPreClosePrice());

      increaseVO.setIncrease(increase);
      increaseVO.setUpDown(unDown);
      increaseVO.setAmplitude(amplitude);
      increaseVOS.add(increaseVO);
    });

    return increaseVOS.stream()
                      .sorted((o1, o2) -> o1.getIncrease().subtract(o2.getIncrease()).intValue())
                      .limit(10)
                      .toList();
  }

  @Override
  public PageResult<MoreVO> more(Integer page, Integer pageSize) {
    //LocalDateTime last = DateTimeUtil.getLastDateTime4Stock(LocalDateTime.now());
    final LocalDateTime last = LocalDateTime.parse("2021-12-30 09:32:00",
                                                   DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    Page<MoreVO> result = PageHelper.startPage(page, pageSize)
                                    .doSelectPage(() -> stockRtInfoMapper.selectMore(last));

    return PageResult.of(result.getTotal(), page, pageSize, result.getResult());
  }

  @Override
  public UpDownVO<OptionVO> updown() {
    //  TODO 开始时间 模拟 2022-01-06 09:25:00

    LocalDateTime begin = LocalDateTime.parse("2022-01-06 09:25:00",
                                              DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    //  TODO 结束时间 模拟 2022-01-06 14:25:00
    LocalDateTime end = LocalDateTime.parse("2022-01-06 14:25:00",
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    List<OptionVO> updownList = stockRtInfoMapper.selectUpOrDown(begin,
                                                                 end,
                                                                 thresholdProperties.getIncrease());
    List<OptionVO> downList = stockRtInfoMapper.selectUpOrDown(begin,
                                                               end,
                                                               thresholdProperties.getDecrease());

    return UpDownVO.of(updownList, downList);
  }

  @Override
  public void export(Integer page, Integer pageSize, HttpServletResponse response)
      throws IOException {
    String fileName = Constants.EXCEL_FILE_PREFIX + page + Constants.EXCEL_FILE_SUFFIX;
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Disposition",
                       "attachment;filename*=UTF-8''" +
                           URLEncoder.encode(fileName, StandardCharsets.UTF_8));

    AtomicInteger counter = new AtomicInteger(1);
    EasyExcel.write(response.getOutputStream(), StockInfoExcelDTO.class)
             .sheet("股票信息")
             .registerWriteHandler(new CustomSheetWriteHandler())
             .doWrite(this.more(page, pageSize).getRows().stream().map(vo -> {
               StockInfoExcelDTO dto = new StockInfoExcelDTO();
               BeanUtil.copyProperties(vo, dto);
               dto.setNum(counter.getAndIncrement());
               return dto;
             }).toList());

  }

  @Override
  public TradeAmtVO<OptionVO> tradeAmt() {
    // 获取当前与前一个交易日的时间区间
    LocalDateTime todayCloseTime = DateTimeUtil.getCloseDate(DateTimeUtil.getLastDateTime4Stock(
        LocalDateTime.now()));
    LocalDateTime todayOpenTime = DateTimeUtil.getOpenDate(todayCloseTime);
    LocalDateTime yesterdayCloseTime = DateTimeUtil.getCloseDate(DateTimeUtil.getPreviousTradingDay(
        todayCloseTime));
    LocalDateTime yesterdayOpenTime = DateTimeUtil.getOpenDate(yesterdayCloseTime);

    // ===== 模拟数据（开发阶段使用） =====
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    todayOpenTime = parseTime("2021-12-28 09:30:00", formatter);
    todayCloseTime = parseTime("2021-12-28 14:30:00", formatter);
    yesterdayOpenTime = parseTime("2021-12-27 09:30:00", formatter);
    yesterdayCloseTime = parseTime("2021-12-27 14:30:00", formatter);
    // ==================================

    List<OptionVO> allRecords = stockMarketIndexInfoMapper.selectTradeCount(yesterdayOpenTime,
                                                                            todayCloseTime,
                                                                            marketProperties.getInner());

    LocalDate today = todayOpenTime.toLocalDate();
    LocalDate yesterday = yesterdayOpenTime.toLocalDate();

    // 拆分为今日与昨日数据
    List<OptionVO> todayList = filterByDate(allRecords, today, formatter);
    List<OptionVO> yesterdayList = filterByDate(allRecords, yesterday, formatter);

    return TradeAmtVO.of(todayList, yesterdayList);
  }

  @Override
  public StockUpDownVO<InfoVO> stockUpdown() {
    LocalDateTime last = DateTimeUtil.getLastDateTime4Stock(LocalDateTime.now());
    // TODO mock data
    last = LocalDateTime.parse("2022-01-06 09:55:00",
                               DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    // TODO mock data
    List<InfoVO> infoVOS = stockRtInfoMapper.selectStockUpDown(last);

    return StockUpDownVO.of(last, infoVOS);
  }


  /**
   * 解析字符串为 LocalDateTime
   */
  private LocalDateTime parseTime(String timeStr, DateTimeFormatter formatter) {
    return LocalDateTime.parse(timeStr, formatter);
  }

  /**
   * 过滤指定日期的数据记录
   */
  private List<OptionVO> filterByDate(List<OptionVO> sourceList,
                                      LocalDate targetDate,
                                      DateTimeFormatter formatter) {
    return sourceList.stream().filter(vo -> {
      LocalDateTime time = LocalDateTime.parse(vo.getTime(), formatter);
      return time.toLocalDate().equals(targetDate);
    }).toList();
  }


}