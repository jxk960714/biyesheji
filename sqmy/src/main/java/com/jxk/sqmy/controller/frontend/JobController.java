package com.jxk.sqmy.controller.frontend;

import com.jxk.sqmy.service.JobService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : JobController  //类名
 * @Description : 这是得到job  //描述
 * @Author : jxk  //作者
 * @Date: 2020-03-03 16:02  //时间
 */
@RestController
@RequestMapping("/frontend")
public class JobController {
    @Autowired
    private JobService jobService;
    @GetMapping("/getjob")
    public Map<String,Object> getjob(@Param("typeid") Integer typeid){
        Map<String,Object> resultMap=new HashMap<>();
        if(jobService.queryJobByTypeId(typeid)!=null){
            resultMap.put("jobList",jobService.queryJobByTypeId(typeid));
            resultMap.put("success",true);
            return resultMap;
        }else {
            return null;
        }
    }
}
