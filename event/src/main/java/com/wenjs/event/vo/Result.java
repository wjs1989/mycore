package com.wenjs.event.vo;

import lombok.Data;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:20
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success(){
        return new Result(0,"成功");
    }

}
