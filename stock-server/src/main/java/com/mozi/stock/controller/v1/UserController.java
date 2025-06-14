package com.mozi.stock.controller.v1;

import com.mozi.stock.api.UserControllerAPI;
import com.mozi.stock.dto.LoginDTO;
import com.mozi.stock.enums.CodeEnum;
import com.mozi.stock.exception.Exceptions;
import com.mozi.stock.response.ResponseResult;
import com.mozi.stock.service.UserService;
import com.mozi.stock.vo.CaptchaVO;
import com.mozi.stock.vo.LoginVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zby
 * @created 2024-06-22 22:48
 * @description 用户接口实现
 */
@RestController
public class UserController implements UserControllerAPI {

  @Resource
  private UserService userService;

  @Override
  public ResponseResult<LoginVO> login(@RequestBody LoginDTO loginDTO) {
    return ResponseResult.ok(userService.login(loginDTO));
  }

  @Override
  public ResponseResult<CaptchaVO> captcha() {
    return ResponseResult.ok(userService.captcha());
  }

  @Override
  public String getName() {
    Exceptions.cast(CodeEnum.TOKEN_PAST_DUE);
    return "mozi";
  }
}
