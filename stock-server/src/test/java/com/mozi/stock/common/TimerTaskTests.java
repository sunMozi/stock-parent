package com.mozi.stock.common;


import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import org.junit.jupiter.api.Test;

/**
 * @author moZiA
 * @date 2025/6/14 8:37
 * @description
 */
public class TimerTaskTests {


  @Test
  public void testWithLatch() throws InterruptedException {
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        System.out.println("我执行了：" + LocalDateTime.now());
      }
    };

    timer.schedule(task, 1000, 1000);

  }


}