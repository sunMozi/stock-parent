package com.mozi.stock.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockTimerServerImplTests {

  @Resource
  private StockTimerServerImpl stockTimerServer;


  @Test
  void getInnerMarketInfo() {

    stockTimerServer.getInnerMarketInfo();

  }
}