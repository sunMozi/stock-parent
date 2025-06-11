package com.mozi.stock.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

/**
 * @author moZiA
 * @date 2025/6/11 11:53
 * @description
 */
@ConfigurationProperties(prefix = "limiter.config")
@Controller
@Setter
@Getter
public class LimiterProperties {

  private int concurrentNum;

}