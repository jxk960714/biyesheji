package com.jxk.sqmy.entity;

import java.io.Serializable;

/**
 * (Job)实体类
 *
 * @author makejava
 * @since 2020-03-03 15:49:43
 */
public class Job implements Serializable {
    private static final long serialVersionUID = -59414585090346267L;
    
    private Integer id;
    
    private String jobname;
    
    private Integer typeid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

}