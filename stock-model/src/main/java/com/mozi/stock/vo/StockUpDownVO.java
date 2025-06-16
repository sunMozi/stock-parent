package com.mozi.stock.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 视图对象：StockUpDownVO
 * 用于返回给前端的响应数据（不包含敏感字段）
 *
 * @author moZiA
 * @date 2025/06/16 11:46
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockUpDownVO<T> implements Serializable {

  private LocalDateTime time;

  private List<T> infos;

  public static <T> StockUpDownVO<T> of(LocalDateTime time, List<T> infos) {
    return StockUpDownVO.<T>builder().time(time).infos(infos).build();
  }


}
