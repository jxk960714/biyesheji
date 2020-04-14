package com.jxk.sqmy.controller;

import com.jxk.sqmy.entity.Fabu;
import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.AdminService;
import com.jxk.sqmy.service.FabuService;
import com.jxk.sqmy.util.JiaMi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
    @Resource
    private AdminService adminService;
    @RequestMapping(value = "sqmyfabulist", method = RequestMethod.GET)
    private String sqmyfabulist() {
        return "sqmyfabulist";
    }
    @RequestMapping(value = "index", method = RequestMethod.GET)
    private String index() {
        return "index";
    }
    @RequestMapping("/403")
    public String to403(){
        return "403";
    }
    @RequestMapping("/aboutme")
    public String aboutme(){
        return "aboutme";
    }
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
        modelAndView.setViewName("sqmyfabulmessage");
        return modelAndView;

    }

    @PostMapping("/xgpassword")
    @ResponseBody
    public  Map<String,Object> xgpassword(Integer userid,String username,String password){
        Map<String ,Object> resultMap =new HashMap<>();
        User user=new User();
        user.setUserid(userid);
        user.setPassword(JiaMi.MD5Pwd(username,password));
        if(adminService.xiugaiuser(user)>0){
           resultMap.put("success",true);
        }
        return resultMap;
    }
}
