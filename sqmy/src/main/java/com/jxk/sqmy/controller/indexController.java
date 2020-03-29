package com.jxk.sqmy.controller;

import com.jxk.sqmy.entity.Fabu;
import com.jxk.sqmy.service.FabuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : indexController  //类名
 * @Description : 首页  //描述
 * @Author : jxk  //作者
 * @Date: 2020-03-09 20:40  //时间
 */
@Controller
public class indexController {
    @Autowired
    private FabuService fabuService;
    @ResponseBody
    @GetMapping("/index/{pageIndex}")
    public Map<String,Object> getFabu( @PathVariable("pageIndex") int PageIndex){
        Map<String ,Object> resultMap =new HashMap<>();
        List<Fabu> fabuList =fabuService.getFabu(PageIndex,20);
        if (fabuList!=null){
            resultMap.put("success",true);
            resultMap.put("fabuList",fabuList);
            return  resultMap;
        }
       return null;
    }
    @GetMapping("/getmessage/{id}")
    public  ModelAndView getMessage( @PathVariable("id") Integer id){
        Fabu fabu=fabuService.queryById(id);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("fabu",fabu);
        modelAndView.setViewName("fabumessage");
        return modelAndView;

    }
}
