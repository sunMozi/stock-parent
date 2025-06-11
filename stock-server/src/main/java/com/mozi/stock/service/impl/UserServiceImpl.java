package com.mozi.stock.service.impl;


import cn.hutool.core.util.RandomUtil;
import com.mozi.stock.service.UserService;
import com.mozi.stock.vo.CapthcVO;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/6/10 21:30
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

  private static final Integer FOUR = 4;

  @Override
  public CapthcVO captcha() {
    String captcha = RandomUtil.randomNumbers(FOUR);
    return null;
  }
}