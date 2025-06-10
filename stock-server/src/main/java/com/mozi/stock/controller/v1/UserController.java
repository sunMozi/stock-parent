package com.mozi.stock.controller.v1;

import com.mozi.stock.api.UserControllerAPI;
import com.mozi.stock.enums.CodeEnum;
import com.mozi.stock.exception.Exceptions;
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
    Exceptions.cast(CodeEnum.TOKEN_PAST_DUE );
    return "itheima";
  }
}
