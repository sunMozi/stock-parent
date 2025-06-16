package com.mozi.stock.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 视图对象：InfoVO
 *
 * 用于返回给前端的响应数据（不包含敏感字段）
 *
 * @author moZiA
 * @date 2025/06/16 11:44
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoVO implements Serializable {

  private Long count;
  private String title;


}
