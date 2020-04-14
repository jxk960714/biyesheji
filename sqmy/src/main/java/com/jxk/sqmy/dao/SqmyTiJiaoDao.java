package com.jxk.sqmy.dao;

import com.jxk.sqmy.entity.Sqmy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/**
 * @ClassName : SqmyTiJiaoDao  //类名
 * @Description :   //这是用户提交社情民意的方法
 * @Author : jxk  //作者
 * @Date: 2020-03-01 10:25  //时间
 */
@Mapper
public interface SqmyTiJiaoDao {
    public int getstatus(@Param("sqmyid") int sqmyid);
    public  boolean fabu(@Param("sqmyid") int sqmyid, @Param("tjtime") String date);
}
