package com.jxk.sqmy.exception;

/**
 * @ClassName : Result  //类名
 * @Description :   //描述
 * @Author : jxk  //作者
 * @Date: 2020-03-01 12:49  //时间
 */
public class Result<T> {
    public static final int ERROR_CODE_UN_SUPPORT_ARGUMENTS_VALUE = -3;
    public static final int ERROR_CODE_UN_SUPPORT_ACTION = -4;
    public static Result SYSTEM_ERR = errorOf(-1, "系统错误");
    int resCode;
    String resMessage;
    T data;

    public static <O> Result<O> sucessOf(O object) {
        Result<O> result = new Result<O>();
        result.resCode = 0;
        result.resMessage = "成功";
        result.data = object;
        return result;
    }

    public static Result errorOf(int errorCode, String message) {
        Result result = new Result();
        result.resCode = errorCode;
        result.resMessage = message;
        return result;
    }
}
