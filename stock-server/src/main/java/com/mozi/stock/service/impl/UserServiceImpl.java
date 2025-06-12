package com.mozi.stock.service.impl;


import static com.mozi.stock.constant.Constants.FOUR;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.mozi.stock.annotation.Limiter;
import com.mozi.stock.cache.Cache;
import com.mozi.stock.constant.Constants;
import com.mozi.stock.dto.LoginDTO;
import com.mozi.stock.entity.SysUser;
import com.mozi.stock.enums.CodeEnum;
import com.mozi.stock.exception.Exceptions;
import com.mozi.stock.mapper.SysUserMapper;
import com.mozi.stock.service.UserService;
import com.mozi.stock.util.IdWorkers;
import com.mozi.stock.util.RedisKeyUtil;
import com.mozi.stock.vo.CaptchaVO;
import com.mozi.stock.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author moZiA
 * @date 2025/6/10 21:30
 * @description
 */
@Service
public class UserServiceImpl implements UserService {


  private final Cache cache;
  private final IdWorkers idWorkers;
  private final SysUserMapper sysUserMapper;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(Cache cache,
                         IdWorkers idWorkers,
                         SysUserMapper sysUserMapper,
                         PasswordEncoder passwordEncoder) {
    this.cache = cache;
    this.idWorkers = idWorkers;
    this.sysUserMapper = sysUserMapper;
    this.passwordEncoder = passwordEncoder;
  }


  @Override
  @Limiter
  public CaptchaVO captcha() {
    String captcha = RandomUtil.randomNumbers(FOUR);
    String redisKey = RedisKeyUtil.generateKey(Constants.CAPTCHA_PREFIX, idWorkers.nextStrId());
    cache.set(redisKey, captcha, Constants.CAPTCHA_EXPIRE_TIME);
    return CaptchaVO.builder().rkey(redisKey).code(captcha).build();
  }

  @Override
  public LoginVO login(LoginDTO loginDTO) {
    if (ObjectUtil.isEmpty(loginDTO) || ObjectUtil.isEmpty(loginDTO.getUsername()) ||
        ObjectUtil.isEmpty(loginDTO.getPassword()) || ObjectUtil.isEmpty(loginDTO.getCode()) ||
        ObjectUtil.isEmpty(loginDTO.getRkey())) {
      Exceptions.cast(CodeEnum.PARAM_ID_NOT_NULL);
    }

    String captcha = cache.get(loginDTO.getRkey());

    if (!StringUtils.equals(captcha, loginDTO.getCode())) {
      Exceptions.cast(CodeEnum.SYSTEM_VERIFY_CODE_ERROR);
    }

    SysUser user = sysUserMapper.selectByUsername(loginDTO.getUsername(),
                                                  Constants.SYS_USER_STATUS_NORMAL,
                                                  Constants.SYS_USER_UN_DELETED);

    if (ObjectUtil.isEmpty(user) ||
        !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
      Exceptions.cast(CodeEnum.SYSTEM_USERNAME_NOT_EXISTS);
    }

    return LoginVO.builder()
                  .id(user.getId())
                  .nickName(user.getNickName())
                  .phone(user.getPhone())
                  .build();
  }
}