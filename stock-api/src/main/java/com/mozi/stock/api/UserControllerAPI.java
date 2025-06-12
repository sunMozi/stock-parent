package com.mozi.stock.api;

import com.mozi.stock.dto.LoginDTO;
import com.mozi.stock.response.ResponseResult;
import com.mozi.stock.vo.CaptchaVO;
import com.mozi.stock.vo.LoginVO;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(path = "/api")
public interface UserControllerAPI {

  @PostMapping(path = "/login")
  ResponseResult<LoginVO> login(LoginDTO loginDTO);


  @GetMapping(path = "/captcha")
  ResponseResult<CaptchaVO> captcha();

  /**
   * 环境测试接口
   *
   * @return String
   */
  @GetMapping(path = "/user/test")
  String getName();


}
