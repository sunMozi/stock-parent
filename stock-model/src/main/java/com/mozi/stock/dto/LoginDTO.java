package com.mozi.stock.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/6/11 14:40
 * @description
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

  private String username;
  private String password;
  private String code;
  private String rkey;

}