package com.mozi.stock.helper;


import com.google.common.util.concurrent.RateLimiter;
import com.mozi.stock.enums.CodeEnum;
import com.mozi.stock.response.ResponseResult;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author moZiA
 * @date 2025/6/11 12:10
 * @description
 */
@Aspect
@Component
@SuppressWarnings("all")
public class LimiterAspect {


  @Resource
  private RateLimiter rateLimiter;


  @Pointcut(value = "@annotation(com.mozi.stock.annotation.Limiter)")
  public void pt() {
  }

  @Around("pt()")
  public Object limiter(ProceedingJoinPoint joinPoint) throws Throwable {
    boolean flag = rateLimiter.tryAcquire();
    if (flag) {
      return joinPoint.proceed();
    } else {
      return ResponseResult.error(CodeEnum.OVER_CONCURRENT_NUM);
    }

  }


}