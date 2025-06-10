package com.mozi.stock.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mozi.stock.enums.Code;
import com.mozi.stock.enums.CodeEnum;
import lombok.Data;

/**
 * @author zby
 * @created 2024-06-21 14:24
 * @description 通用接口响应对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    /**
     * 响应编码
     */
    private int code;
    /**
     * 响应描述
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;


    /**
     * 接口响应失败，本来接口就失败了肯定不携带数据，只会响应自定义错误信息
     *
     * @param <T> 响应数据类型
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(Code code) {
        return common(code, null);
    }


    /**
     * 接口响应成功，不携带数据,比如增、删、改接口
     *
     * @param <T> 响应数据类型
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> ok() {
        return common(CodeEnum.SUCCESS, null);
    }

    /**
     * 接口响应成功，携带数据，比如查询接口
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> ok(T data) {
        return common(CodeEnum.SUCCESS, data);
    }

    /**
     * 自定义响应描述，携带数据
     *
     * @param code 响应枚举
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return ResponseResult
     */
    private static <T> ResponseResult<T> common(Code code, T data) {
        return new ResponseResult<>(code, data);
    }

    /**
     * 通用响应对象构造器
     *
     * @param code 通用响应接口（多态，使用时传入枚举实现即可）
     * @param data 响应数据
     */
    private ResponseResult(Code code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }


}
