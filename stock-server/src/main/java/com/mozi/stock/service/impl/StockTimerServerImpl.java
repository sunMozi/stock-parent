package com.mozi.stock.service.impl;

import com.mozi.stock.entity.StockMarketIndexInfo;
import com.mozi.stock.enums.CodeEnum;
import com.mozi.stock.exception.Exceptions;
import com.mozi.stock.mapper.StockMarketIndexInfoMapper;
import com.mozi.stock.properties.MarketProperties;
import com.mozi.stock.service.StockTimerServer;
import com.mozi.stock.util.IdWorkers;
import com.mozi.stock.util.SafeConvertUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockTimerServerImpl implements StockTimerServer {

  private final RestTemplate restTemplate;
  private final StockMarketIndexInfoMapper stockMarketIndexInfoMapper;
  private final IdWorkers idWorkers;
  private final MarketProperties marketProperties;

  private static final String SINA_MARKET_URL = "https://hq.sinajs.cn/list=";
  private static final Pattern MARKET_DATA_PATTERN = Pattern.compile("var hq_str_(.+?)=\"(.+?)\";");

  @Override
  public void getInnerMarketInfo() {
    try {
      String rawData = fetchMarketDataFromSina();
      List<StockMarketIndexInfo> parsedData = parseSinaMarketData(rawData);

      if (parsedData.isEmpty()) {
        log.warn("未解析到任何大盘数据，响应原文：{}", rawData);
        Exceptions.cast(CodeEnum.MARKET_DATA_EMPTY);
      }

      stockMarketIndexInfoMapper.insertBatch(parsedData);
      log.info("成功写入大盘指数数据，共 {} 条", parsedData.size());

    } catch (Exception ex) {
      log.error("获取大盘指数信息失败", ex);
      Exceptions.cast(CodeEnum.STOCK_INDEX_FETCH_FAILED);
    }
  }

  /**
   * 拉取大盘行情原始数据
   */
  private String fetchMarketDataFromSina() {
    List<String> indexCodes = marketProperties.getInner();
    if (indexCodes == null || indexCodes.isEmpty()) {
      log.error("未配置大盘指数代码，marketProperties.inner=null 或 empty");
      Exceptions.cast(CodeEnum.STOCK_INDEX_NOT_CONFIGURED);
    }

    String fullUrl = SINA_MARKET_URL + String.join(",", indexCodes);
    HttpEntity<Void> entity = new HttpEntity<>(buildRequestHeaders());

    ResponseEntity<String> response = restTemplate.exchange(fullUrl,
                                                            HttpMethod.GET,
                                                            entity,
                                                            String.class);

    return Optional.ofNullable(response.getBody())
                   .orElseThrow(() -> new RuntimeException("大盘行情数据响应体为空"));
  }

  /**
   * 解析新浪返回的大盘数据内容
   */
  private List<StockMarketIndexInfo> parseSinaMarketData(String rawData) {
    Matcher matcher = MARKET_DATA_PATTERN.matcher(rawData);
    List<StockMarketIndexInfo> resultList = new ArrayList<>();

    while (matcher.find()) {
      String code = matcher.group(1);
      String[] fields = matcher.group(2).split(",");

      if (fields.length < 6) {
        log.warn("字段数量异常，code={}，原始数据={}", code, Arrays.toString(fields));
        continue;
      }

      try {
        StockMarketIndexInfo info = StockMarketIndexInfo.builder()
                                                        .id(idWorkers.nextStrId())
                                                        .markId(code)
                                                        .markName(fields[0])
                                                        .curPoint(SafeConvertUtil.toBigDecimal(
                                                            fields[1]))
                                                        .currentPrice(SafeConvertUtil.toBigDecimal(
                                                            fields[2]))
                                                        .updownRate(SafeConvertUtil.toBigDecimal(
                                                            fields[3]))
                                                        .tradeAccount(SafeConvertUtil.toLong(fields[4]))
                                                        .tradeVolume(SafeConvertUtil.toLong(fields[5]))
                                                        .curTime(LocalDateTime.now())
                                                        .build();

        resultList.add(info);
      } catch (Exception ex) {
        log.error("解析大盘行情失败，code={}，原始数据={}，异常信息={}",
                  code,
                  Arrays.toString(fields),
                  ex.getMessage(),
                  ex);
      }
    }

    return resultList;
  }

  /**
   * 构建 HTTP 请求头
   */
  private HttpHeaders buildRequestHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.REFERER, "https://finance.sina.com.cn/stock/");
    headers.set(HttpHeaders.USER_AGENT,
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
    return headers;
  }

}
