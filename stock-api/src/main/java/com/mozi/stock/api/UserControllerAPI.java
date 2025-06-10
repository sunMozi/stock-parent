package com.mozi.stock.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(path = "/api")
public interface UserControllerAPI {

  /**
   * 环境测试接口
   *
   * @return String
   */
  @GetMapping(path = "/user/test")
  String getName();


}
