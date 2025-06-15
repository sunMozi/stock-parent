package com.mozi.stock.properties;


import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

/**
 * @author moZiA
 * @date 2025/6/11 11:53
 * @description
 */
@ConfigurationProperties(prefix = "stock.config.market.threshold")
@Controller
@Setter
@Getter
public class ThresholdProperties {

  /**
   * 涨幅判断阈值，例如 0.10 表示 10%
   */
  private Double increase;

  /**
   * 跌幅判断阈值，例如 -0.10 表示 -10%
   */
  private Double decrease;

}