package com.jxk.sqmy.config.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName : Test  //类名
 * @Description :   //描述
 * @Author : jxk  //作者
 * @Date: 2020-03-31 20:18  //时间
 */
public class Test {
    /**
     * 账户密码加密
     * @param username
     * @param pwd
     * @return
     */
    public static String MD5Pwd(String username, String pwd) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = pwd;//密码原值
        ByteSource salt = ByteSource.Util.bytes(username);//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        System.out.println(new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations).toString());
        return new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations).toString();

    }

    public static void main(String[] args) {
        MD5Pwd("王五","123456");
    }
}
