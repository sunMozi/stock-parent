package com.mozi.stock.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author mozi
 * @description 日期时间工具类
 */
@SuppressWarnings("all")
public class DateTimeUtil {

  /**
   * 节假日集合
   */
  private static final List<LocalDate> HOLIDAYS;

  /*
   *  静态代码块中提供2021 、 2022 和 2024 年节假日
   *  测试数据 是2021 和2022 数据，今年是2024采集的需要使用
   *  实际开发中 会调用专门的节假日接口获取数据
   */
  static {
    HOLIDAYS = Arrays.asList(
        // 2021 年A股非休息日 休市 时间表
        LocalDate.of(2021, 1, 1),
        LocalDate.of(2021, 2, 11),
        LocalDate.of(2021, 2, 12),
        LocalDate.of(2021, 2, 15),
        LocalDate.of(2021, 2, 16),
        LocalDate.of(2021, 2, 17),
        LocalDate.of(2021, 4, 5),
        LocalDate.of(2021, 5, 3),
        LocalDate.of(2021, 5, 4),
        LocalDate.of(2021, 5, 5),
        LocalDate.of(2021, 6, 14),
        LocalDate.of(2021, 9, 20),
        LocalDate.of(2021, 9, 21),
        LocalDate.of(2021, 10, 1),
        LocalDate.of(2021, 10, 4),
        LocalDate.of(2021, 10, 5),
        LocalDate.of(2021, 10, 6),
        LocalDate.of(2021, 10, 7),
        // 2022 年A股非休息日 休市 时间表
        LocalDate.of(2022, 1, 3),
        LocalDate.of(2022, 1, 31),
        LocalDate.of(2022, 2, 1),
        LocalDate.of(2022, 2, 2),
        LocalDate.of(2022, 2, 3),
        LocalDate.of(2022, 2, 4),
        LocalDate.of(2022, 4, 4),
        LocalDate.of(2022, 4, 5),
        LocalDate.of(2022, 5, 2),
        LocalDate.of(2022, 5, 3),
        LocalDate.of(2022, 5, 4),
        LocalDate.of(2022, 6, 3),
        LocalDate.of(2022, 9, 12),
        LocalDate.of(2022, 10, 3),
        LocalDate.of(2022, 10, 4),
        LocalDate.of(2022, 10, 5),
        LocalDate.of(2022, 10, 6),
        LocalDate.of(2022, 10, 7),
        // 2024年A股非休息日 休市 时间表
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 2, 10),
        LocalDate.of(2024, 2, 11),
        LocalDate.of(2024, 2, 12),
        LocalDate.of(2024, 2, 13),
        LocalDate.of(2024, 2, 14),
        LocalDate.of(2024, 2, 15),
        LocalDate.of(2024, 2, 16),
        LocalDate.of(2024, 2, 17),
        LocalDate.of(2024, 2, 18),
        LocalDate.of(2024, 4, 4),
        LocalDate.of(2024, 4, 5),
        LocalDate.of(2024, 5, 1),
        LocalDate.of(2024, 5, 2),
        LocalDate.of(2024, 5, 3),
        LocalDate.of(2024, 6, 10),
        // 2025 年 A股非休息日休市时间表
        LocalDate.of(2025, 1, 1),
        LocalDate.of(2025, 1, 28),
        LocalDate.of(2025, 1, 29),
        LocalDate.of(2025, 1, 30),
        LocalDate.of(2025, 1, 31),
        LocalDate.of(2025, 2, 1),
        LocalDate.of(2025, 2, 2),
        LocalDate.of(2025, 2, 3),
        LocalDate.of(2025, 2, 4),
        LocalDate.of(2025, 4, 4),
        LocalDate.of(2025, 4, 5),
        LocalDate.of(2025, 4, 6),
        LocalDate.of(2025, 5, 1),
        LocalDate.of(2025, 5, 2),
        LocalDate.of(2025, 5, 3),
        LocalDate.of(2025, 5, 4),
        LocalDate.of(2025, 5, 5),
        LocalDate.of(2025, 5, 31),
        LocalDate.of(2025, 6, 1),
        LocalDate.of(2025, 6, 2),
        LocalDate.of(2025, 10, 1),
        LocalDate.of(2025, 10, 2),
        LocalDate.of(2025, 10, 3),
        LocalDate.of(2025, 10, 4),
        LocalDate.of(2025, 10, 5),
        LocalDate.of(2025, 10, 6),
        LocalDate.of(2025, 10, 7),
        LocalDate.of(2025, 10, 8));
  }


  /**
   * 判断指定日期是否是节假日
   *
   * @param target 指定日期
   * @return 是 true ,否则false
   */
  public static boolean isHoliday(LocalDateTime target) {
    return HOLIDAYS.contains(target.toLocalDate());
  }


  /**
   * 获取指定日期下股票的上一个有效交易日时间 （T-1 日）
   *
   * @param target 指定日期
   * @return LocalDateTime
   */
  public static LocalDateTime getPreviousTradingDay(LocalDateTime target) {
    int value = target.getDayOfWeek().getValue();
    LocalDateTime preTradingDay;
    switch (value) {
      // 如果是周1 减 3天  是 T-1
      case 1: {
        preTradingDay = target.minusDays(3);
      }
      // 如果是周1 减 2天  是 T-1
      case 7: {
        preTradingDay = target.minusDays(2);
      }
      // 周2，周3，周4，周5，周6， 减1 是 T-1
      default:
        preTradingDay = target.minusDays(1);
    }
    // 如果T-1 日是 节假日 或者 双休日 则递归
    if (isHoliday(preTradingDay) || isWeekend(preTradingDay)) {
      preTradingDay = getPreviousTradingDay(preTradingDay);
    }
    return getDateTimeWithoutSecond(preTradingDay);
  }

  /**
   * 获取上一天日期
   *
   * @param target 指定日期
   * @return LocalDateTime
   */
  public static LocalDateTime getPreDateTime(LocalDateTime target) {
    return target.minusDays(1);
  }

  /**
   * 日期转String
   *
   * @param target  日期
   * @param pattern 日期格式
   * @return String
   */
  public static String parseToString(LocalDateTime target, String pattern) {
    return target.format(DateTimeFormatter.ofPattern(pattern));
  }

  /**
   * 获取股票日期格式字符串
   *
   * @param target 指定日期
   * @return String
   */
  public static String parseToString4Stock(LocalDateTime target) {
    return parseToString(target, "yyyyMMddHHmmss");
  }

  /**
   * 获取最近的股票有效时间，精确到分钟
   *
   * @param target 时间
   * @return String
   */
  public static String getLastDateString4Stock(LocalDateTime target) {
    return parseToString4Stock(getDateTimeWithoutSecond(getLastDateTime4Stock(target)));
  }

  /**
   * 获取最近的股票有效时间，精确到分钟
   *
   * @param target 时间
   * @return LocalDateTime
   */
  public static LocalDateTime getLastDateTime4Stock(LocalDateTime target) {
    // 判断是否是工作日
    if (isWorkDay(target)) {
      // 当前日期开盘前
      if (target.isBefore(getOpenDate(target))) {
        target = getCloseDate(getPreviousTradingDay(target));
      } else if (isMarketOffTime(target)) {
        target = target.withHour(11).withMinute(30).withSecond(0).withNano(0);
      } else if (target.isAfter(getCloseDate(target))) {
        // 当前日期收盘后
        target = getCloseDate(target);
      }
    } else {
      target = getCloseDate(getPreviousTradingDay(target));
    }
    target = getDateTimeWithoutSecond(target);

    return target;
  }

  /**
   * 判断指定时间是否是双休日
   *
   * @param target 指定时间
   * @return 双休日 true 否则false
   */
  public static boolean isWeekend(LocalDateTime target) {
    return target.getDayOfWeek().getValue() == DayOfWeek.SATURDAY.getValue() ||
        target.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue();
  }

  /**
   * 判断指定日期是否是工作日
   *
   * @param target 指定日期
   * @return 是工作日 true 否则 false
   */
  public static boolean isWorkDay(LocalDateTime target) {
    int value = target.getDayOfWeek().getValue();
    return (value >= DayOfWeek.MONDAY.getValue() && value <= DayOfWeek.FRIDAY.getValue());
  }

  /**
   * 获取指定日期的收盘时间
   *
   * @param target 指定日期
   * @return 比如 2024-07-26 17:34:00  => 2024-07-26 15:00
   */
  public static LocalDateTime getCloseDate(LocalDateTime target) {
    return target.withHour(15).withMinute(0).withSecond(0).withNano(0);
  }

  /**
   * 获取指定日期的开盘时间
   *
   * @param target 指定日期
   * @return 比如 2024-07-26 17:34:00  => 2024-07-26 09:30
   */
  public static LocalDateTime getOpenDate(LocalDateTime target) {
    return target.withHour(9).withMinute(30).withSecond(0).withNano(0);
  }


  /**
   * 判断当前时间是否在大盘的中午休盘时间段,大盘午休时间段是 11:30 - 13：00
   *
   * @return boolean
   */
  public static boolean isMarketOffTime(LocalDateTime target) {
    LocalDateTime start = target.withHour(11).withMinute(30).withSecond(0).withNano(0);
    LocalDateTime end = target.withHour(13).withMinute(0).withSecond(0).withNano(0);
    return target.isAfter(start) && target.isBefore(end);
  }

  /**
   * 将秒归0
   *
   * @param target 指定日期
   * @return 比如，给定 2024-06-20 12:14:45 => 2024-06-20 12:14:00
   */
  public static LocalDateTime getDateTimeWithoutSecond(LocalDateTime target) {
    return target.withSecond(0).withNano(0);
  }

  /**
   * 将秒归0
   *
   * @param dateTimeStr 指定日期字符串，格式必须是：yyyy-MM-dd HH:mm:ss
   * @return 比如，给定 2024-06-20 12:14:45 => 2024-06-20 12:14:00
   */
  public static LocalDateTime getDateTimeWithoutSecond(String dateTimeStr) {
    LocalDateTime parseDateTime = LocalDateTime.parse(dateTimeStr,
                                                      DateTimeFormatter.ofPattern(
                                                          "yyyy-MM-dd HH:mm:ss"));
    return getDateTimeWithoutSecond(parseDateTime);
  }


}
