package com.jxk.sqmy.entity;

/**
 * @ClassName : Data  //类名
 * @Description :   //描述
 * @Author : jxk  //作者
 * @Date: 2020-04-13 17:30  //时间
 */
public class Data {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Data(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
