package com.work.miaosha.result;

import lombok.Data;

@Data
public class Result<T> {
    private int code;

    private String msg;

    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(CodeMsg cm) {
        if (cm == null){
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /**
     * 成功时的调用
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 失败时的调用
     */
    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }
}
