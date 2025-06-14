package com.mozi.stock.config;


import com.mozi.stock.properties.IdWorkersProperties;
import com.mozi.stock.util.IdWorkers;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author moZiA
 * @date 2025/6/11 11:36
 * @description
 */
@Configurable
public class IdWorkersConfiguration {

  @Bean
  public IdWorkers idWorkers(IdWorkersProperties idWorkersProperties) {

    return new IdWorkers(idWorkersProperties.getWorkerId(), idWorkersProperties.getDatacenterId());

  }

}