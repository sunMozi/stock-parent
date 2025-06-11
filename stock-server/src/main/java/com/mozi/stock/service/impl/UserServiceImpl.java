package com.mozi.stock.service.impl;


import static com.mozi.stock.constant.Constants.FOUR;

import cn.hutool.core.util.RandomUtil;
import com.mozi.stock.annotation.Limiter;
import com.mozi.stock.cache.Cache;
import com.mozi.stock.constant.Constants;
import com.mozi.stock.service.UserService;
import com.mozi.stock.util.IdWorkers;
import com.mozi.stock.util.RedisKeyUtil;
import com.mozi.stock.vo.CaptchaVO;
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

  @Resource
  private IdWorkers idWorkers;

  @Override
  @Limiter
  public CaptchaVO captcha() {
    String captcha = RandomUtil.randomNumbers(FOUR);
    String redisKey = RedisKeyUtil.generateKey(Constants.CAPTCHA_PREFIX, idWorkers.nextStrId());
    cache.set(redisKey, captcha, Constants.CAPTCHA_EXPIRE_TIME);
    return CaptchaVO.builder().rkey(redisKey).code(captcha).build();
  }
}