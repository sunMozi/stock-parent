package com.mozi.stock.service.impl;


import static com.mozi.stock.constant.Constants.FOUR;

import cn.hutool.core.util.RandomUtil;
import com.mozi.stock.cache.Cache;
import com.mozi.stock.service.UserService;
import com.mozi.stock.vo.CapthcVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/6/10 21:30
 * @description
 */
@Service
public class UserServiceImpl implements UserService {


  @Resource
  private Cache cache;

  @Override
  public CapthcVO captcha() {
    String captcha = RandomUtil.randomNumbers(FOUR);
    cache.set(captcha, captcha);
    return null;
  }
}