package com.mozi.stock.controller;

import com.mozi.stock.api.UserControllerAPI;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zby
 * @created 2024-06-22 22:48
 * @description 用户接口实现
 */
@RestController
public class UserController implements UserControllerAPI {

  @Override
  public String getName() {

    return "itheima";
  }
}
