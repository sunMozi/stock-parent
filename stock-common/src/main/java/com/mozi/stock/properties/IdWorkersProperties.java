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
@ConfigurationProperties(prefix = "id-workers.config")
@Controller
@Setter
@Getter
public class IdWorkersProperties {

  private long datacenterId;
  private long workerId;

}