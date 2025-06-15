package com.mozi.stock.common;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;

/**
 * @author moZiA
 * @date 2025/6/14 8:37
 * @description
 */
public class TimerTaskTests {


  @Test
  public void testWithLatch() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        System.out.println("定时任务执行了");
        latch.countDown(); // 通知主线程继续执行
      }
    };

    timer.schedule(task, 1000);

    // 最多等待 2 秒
    latch.await();
  }


}