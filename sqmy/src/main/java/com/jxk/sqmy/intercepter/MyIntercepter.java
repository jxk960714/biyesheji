package com.jxk.sqmy.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxk.sqmy.service.UserService;
import com.jxk.sqmy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.jxk.sqmy.dao.UserDao;
import com.jxk.sqmy.entity.User;

import java.io.PrintWriter;
@Component
public class MyIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception { // TODOAuto - generated method stub
       /* UserService userService=new UserServiceImpl();
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        User user1=userService.queryUser(user);
        System.out.println(user1);
        if (user1!= null) {
            return true;
        } else {
          PrintWriter out= response.getWriter();
          out.print("登录错误");
          return false;
        }*/
        System.out.println("拦截器执行了");
       return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception { // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler,
                modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception { // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
