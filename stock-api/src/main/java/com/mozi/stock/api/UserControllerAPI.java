package com.mozi.stock.api;

import com.mozi.stock.response.ResponseResult;
import com.mozi.stock.vo.CapthcVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(path = "/api")
public interface UserControllerAPI {


  @GetMapping(path = "/captcha")
  ResponseResult<CapthcVO> captcha();

  /**
   * 环境测试接口
   *
   * @return String
   */
  @GetMapping(path = "/user/test")
  String getName();


}
