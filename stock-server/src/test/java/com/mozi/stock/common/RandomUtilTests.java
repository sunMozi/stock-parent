package com.mozi.stock.common;


import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;

/**
 * @author moZiA
 * @date 2025/6/11 8:20
 * @description
 */
public class RandomUtilTests {

  @Test
  public void test() {
    for (int i = 0; i < 10; i++) {
      System.out.println(RandomUtil.randomString(6));
    }
  }

}