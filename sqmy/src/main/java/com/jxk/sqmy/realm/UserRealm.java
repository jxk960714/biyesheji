package com.jxk.sqmy.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.jxk.sqmy.entity.Role;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.UserService;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
// 这里做权限控制
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.queryUserByUserName(username);
        info.addRole(user.getRole().getRoloName());
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
// 这里做登录控制
        AuthenticationInfo info;
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        User user = userService.queryUserByUserName(username);
        String userPwd=user.getPassword();
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }

        ByteSource salt = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(username, userPwd, salt, getName());
    }

}
