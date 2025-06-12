package com.mozi.stock.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/6/11 14:41
 * @description
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {

  private String id;
  private String username;
  private String nickName;
  private String phone;


}