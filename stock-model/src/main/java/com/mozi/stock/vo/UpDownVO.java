package com.mozi.stock.vo;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moZiA
 * @date 2025/6/15 10:23
 * @description
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpDownVO<T> {

  private List<T> upList;
  private List<T> downList;

  public static <T> UpDownVO<T> of(List<T> upList, List<T> downList) {
    return new UpDownVO<>(upList, downList);
  }

}