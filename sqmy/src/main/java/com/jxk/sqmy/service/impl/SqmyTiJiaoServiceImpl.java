package com.jxk.sqmy.service.impl;

import com.jxk.sqmy.dao.SqmyTiJiaoDao;
import com.jxk.sqmy.service.SqmyTiJiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName : SqmyFaBuServiceImpl  //类名
 * @Description :   //描述
 * @Author : jxk  //作者
 * @Date: 2020-03-01 10:35  //时间
 */
@Service
public class SqmyTiJiaoServiceImpl implements SqmyTiJiaoService {
    @Autowired
    private SqmyTiJiaoDao sqmyTiJiaoDao;
    @Override
    public Boolean tijiao(int sqmyid, Date tjtime) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        int status = sqmyTiJiaoDao.getstatus(sqmyid);
        if(status==0){
            return sqmyTiJiaoDao.fabu(sqmyid,dateFormat.format(tjtime));
        }else {
            return false;
        }
    }



}
