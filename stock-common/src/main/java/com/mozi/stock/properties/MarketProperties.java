package com.mozi.stock.properties;


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
@ConfigurationProperties(prefix = "stock.config.market")
@Controller
@Setter
@Getter
public class MarketProperties {


  /**
   * A 股国内大盘编码集合
   */
  private List<String> inner;
  /**
   * 外盘编码集合
   */
  private List<String> outer;


}