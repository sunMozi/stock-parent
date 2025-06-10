package com.mozi.stock.common;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author moZiA
 * @date 2025/6/10 21:10
 * @description
 */

@SpringBootTest
public class PasswordEncoderTests {

  @Resource
  private PasswordEncoder passwordEncoder;

  @Test
  public void test() {
    String password = "123456";
    String encode = passwordEncoder.encode(password);
    System.out.println(encode);
    boolean matches = passwordEncoder.matches(password, encode);
    System.out.println(matches);
  }

}