package com.mozi.stock.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/6/10 21:25
 * @description
 */
@Setter
@Getter
@Builder
public class CaptchaVO {

  private String code;

  private String rkey;

}