package com.mozi.stock.exception;

import com.mozi.stock.enums.Code;
import lombok.Getter;

/**
 * @author zby
 * @created 2024-06-21 15:42
 * @description 项目自定义异常
 */
@Getter
public class StockException extends RuntimeException {

    /**
     * 自定义响应信息
     */
    private final Code code;

    public StockException(Code code) {
        this.code = code;
    }
}
