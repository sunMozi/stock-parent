package com.mozi.stock.vo;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 视图对象：tradeAmtVO
 *
 * 用于返回给前端的响应数据（不包含敏感字段）
 *
 * @author moZiA
 * @date 2025/06/15 14:05
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeAmtVO<T> implements Serializable {

  private List<T> volList;
  private List<T> yesVolList;

  public static <T> TradeAmtVO<T> of(List<T> volList, List<T> yesVolList) {
    return TradeAmtVO.<T>builder()
        .volList(volList)
        .yesVolList(yesVolList)
        .build();
  }


}
