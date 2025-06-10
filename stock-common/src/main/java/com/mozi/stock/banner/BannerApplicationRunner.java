package com.mozi.stock.banner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zby
 * @created 2024-06-17 19:42
 * @description 项目启动成功后打印项目 logo
 */
@Slf4j
@Component
public class BannerApplicationRunner implements ApplicationRunner {

  /**
   * Spring 容器启动完成后执行该类中run 方法
   *
   * @param args incoming application arguments
   */
  @Override
  public void run(ApplicationArguments args) {
    log.info(BANNER);
  }


  /**
   * 项目logo 常量
   */
  public static final String BANNER = """
      The project is starting...
       _______        _               _____           _          \s
      |__   __|      | |             |_   _|         | |         \s
         | | ___   __| | __ _ _   _    | |  _ __   __| | _____  __
         | |/ _ \\ / _` |/ _` | | | |   | | | '_ \\ / _` |/ _ \\ \\/ /
         | | (_) | (_| | (_| | |_| |  _| |_| | | | (_| |  __/>  <\s
         |_|\\___/ \\__,_|\\__,_|\\__, | |_____|_| |_|\\__,_|\\___/_/\\_\\
                               __/ |                             \s
                              |___/                              \s
      -------------The project was started successfully.-----------""";

}
