package com.mozi.stock.handler;


import com.mozi.stock.enums.CodeEnum;
import com.mozi.stock.exception.StockException;
import com.mozi.stock.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zby
 * @created 2024-06-21 23:44
 * @description 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 处理业务异常
   *
   * @param e 异常信息
   * @return ResponseResult
   */
  @ExceptionHandler(value = StockException.class)
  public ResponseResult<?> handleException(StockException e) {
    return ResponseResult.error(e.getCode());
  }


  /**
   * 处理未知异常
   *
   * @param e 异常信息
   * @return ResponseResult
   */
  @ExceptionHandler(value = Exception.class)
  @SuppressWarnings("all")
  public ResponseResult<?> handleUnKnownException(Exception e) {
    log.error(e.getMessage(), e);
    // TODO
    e.printStackTrace();
    return ResponseResult.error(CodeEnum.SYSTEM_ERROR);
  }

}
