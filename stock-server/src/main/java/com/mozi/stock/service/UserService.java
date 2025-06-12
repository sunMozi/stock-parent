package com.mozi.stock.service;

import com.mozi.stock.dto.LoginDTO;
import com.mozi.stock.vo.CaptchaVO;
import com.mozi.stock.vo.LoginVO;

/**
 * @author MoZi
 * @createTime 2025/6/10 21:30
 */
public interface UserService {

  CaptchaVO captcha();

  LoginVO login(LoginDTO loginDTO);
}
