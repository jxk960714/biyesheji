package com.jxk.sqmy.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : CodeUtil  //类名
 * @Description :   //描述
 * @Author : jxk  //作者
 * @Date: 2020-03-16 14:51  //时间
 */
public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request,
                "verifyCodeActual");
        if (verifyCodeActual == null
                || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}