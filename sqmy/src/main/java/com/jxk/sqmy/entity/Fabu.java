package com.jxk.sqmy.entity;

import java.io.Serializable;

/**
 * (Fabu)实体类
 *
 * @author makejava
 * @since 2020-03-01 19:11:51
 */
public class Fabu implements Serializable {
    private static final long serialVersionUID = 397695893466884809L;
    
    private Integer id;
    
    private String sqmyTitle;
    
    private String sqmyZw;
    
    private String sqmyCategory;
    
    private Integer sqmyJjcd;
    private  String keyWord;
    private  String faBuTime;
    private  String xxly;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSqmyTitle() {
        return sqmyTitle;
    }

    public void setSqmyTitle(String sqmyTitle) {
        this.sqmyTitle = sqmyTitle;
    }

    public String getSqmyZw() {
        return sqmyZw;
    }

    public void setSqmyZw(String sqmyZw) {
        this.sqmyZw = sqmyZw;
    }

    public String getSqmyCategory() {
        return sqmyCategory;
    }

    public void setSqmyCategory(String sqmyCategory) {
        this.sqmyCategory = sqmyCategory;
    }

    public Integer getSqmyJjcd() {
        return sqmyJjcd;
    }

    public void setSqmyJjcd(Integer sqmyJjcd) {
        this.sqmyJjcd = sqmyJjcd;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getFaBuTime() {
        return faBuTime;
    }

    public void setFaBuTime(String faBuTime) {
        this.faBuTime = faBuTime;
    }

    public String getXxly() {
        return xxly;
    }

    public void setXxly(String xxly) {
        this.xxly = xxly;
    }
}