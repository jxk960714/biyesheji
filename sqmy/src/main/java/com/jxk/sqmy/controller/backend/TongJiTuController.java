package com.jxk.sqmy.controller.backend;

import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.AdminService;
import com.jxk.sqmy.service.SqmyService;
import com.jxk.sqmy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ClassName : TongJiTu  //类名
 * @Description : 得到统计图  //描述
 * @Author : jxk  //作者
 * @Date: 2020-03-05 14:33  //时间
 */
@RestController
@RequestMapping("/backend")
public class TongJiTuController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SqmyService sqmyService;
    @GetMapping("/zhuxing")
    public Result<Map>getZhuxing(){
        List<User> userList=adminService.queryExceptadmin();

        List<String> usernameList=new LinkedList<>();
        List<Integer> countList=adminService.getSqmyCountByUserAndAtatus();
        for (int i = 0; i < userList.size(); i++) {
            usernameList.add(userList.get(i).getName());
        }
        Map<String,Object> resultMap=new HashMap();
        resultMap.put("usernameList",usernameList);
        resultMap.put("countList",countList);
        return new Result<Map>("成功",resultMap);

    }
    @GetMapping("/zhexian")
    public Map<String,Object> getZhexian(){
        List<Category> categoryList=sqmyService.getCategory();
        List<String> categorynameList=new LinkedList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            categorynameList.add(categoryList.get(i).getCategoryName());
        }
        List<Integer> countList=adminService.getSqmyCountByCategoryAndStatus();
        Map<String,Object> resultMap=new HashMap();
        resultMap.put("categorynameList",categorynameList);
        resultMap.put("countList",countList);
        return resultMap;
    }
}
