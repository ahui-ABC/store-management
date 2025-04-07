package com.ahui.result;

import lombok.Data;

/**
 * 统一返回结果
 * @param <T> 泛型数据类型
 */
@Data
public class Result<T> {
    private int code;    // 状态码（200表示成功）
    private String msg;  // 提示信息
    private T data;      // 返回数据

    // 快速构建成功响应
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setData(data);
        return result;
    }

    // 快速构建失败响应
    public static <T> Result<T> fail(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
