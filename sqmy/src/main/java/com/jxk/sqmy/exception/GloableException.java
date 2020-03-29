package com.jxk.sqmy.exception;



import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
@ControllerAdvice
public class GloableException {
    @ExceptionHandler(Exception.class)
    public Object errorHandler(HttpServletRequest reqest,
                               HttpServletResponse response, Exception e) throws Exception {
        if (isAjax(reqest)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            //具体操作
            writer.write(JSON.toJSONString(Result.errorOf(HttpStatus.NOT_FOUND.value(), e.getMessage())));
            //
            writer.flush();
            writer.close();
            return null;
            //return Result.errorOf(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e.getMessage());
            mav.addObject("url", reqest.getRequestURL());
            mav.setViewName("exception/sysexception");
            return mav;
        }
    }

    public static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals(httpRequest.getHeader("X-Requested-With").toString()));
    }

   /* @ResponseBody
    public ModelAndView myExceptionHandler(Exception ex) {
        MyException e = null;
        if (ex instanceof MyException) {
            e = (MyException) ex;
        } else {
            e = new MyException("系统正在维护");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("errMsg", e.getMsg());
        mv.setViewName("exception/sysexception");
        return mv;
    }*/
}
