package com.jxk.sqmy.util;

/**
 * @ClassName : Result  //类名
 * @Description : 得到的结果  //描述
 * @Author : jxk  //作者
 * @Date: 2020-03-05 14:34  //时间
 */
public class Result<T> {
    private   String meg;
    private T data;

    public Result(String meg, T date) {
        this.meg = meg;
        this.data = date;
    }

    public String getMeg() {
        return meg;
    }

    public void setMeg(String meg) {
        this.meg = meg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
