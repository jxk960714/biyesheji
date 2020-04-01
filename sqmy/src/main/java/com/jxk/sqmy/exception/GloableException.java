package com.jxk.sqmy.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@ControllerAdvice
public class GloableException {
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();
        //判断请求是否为Ajax请求
        if (isAjax(request)) { //如果是的话，就直接返回错误信息
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("exception/sysexception"); //这里需要在templates文件夹下新建一个error.html文件用作错误页面
            return modelAndView;
        } else { //如果不是的话，就跳转到错误页面
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("exception/sysexception"); //这里需要在templates文件夹下新建一个error.html文件用作错误页面
            return modelAndView;
        }
    }

    /**
     * 判断是否是Ajax请求
     *
     * @param request
     * @return
     */
    public boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

}
