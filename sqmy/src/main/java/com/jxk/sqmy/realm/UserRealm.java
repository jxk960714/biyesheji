package com.jxk.sqmy.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.jxk.sqmy.entity.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.UserService;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

// 这里做权限控制
		/*
		 * SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(); String
		 * username=(String) principals.getPrimaryPrincipal(); User user
		 * =userservice.selectUser(username); Integer roleId=user.getRoleId(); Role role
		 * =roleMapper.selectById(roleId); info.addRole(role.getRolename());
		 * Wrapper<Permission> wrapper=new EntityWrapper<>();
		 * System.out.println(role.getId()); wrapper.eq("roleid", role.getId());
		 * List<Permission> permissionList=perMapper.selectList(wrapper);
		 * System.out.println(permissionList.get(0)); List<String> perlist=new
		 * ArrayList<>(); permissionList.forEach(per->{ perlist.add(per.getPername());
		 * }); info.addStringPermissions(perlist); return info;
		 */
		/*SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
		String username=(String)principals.getPrimaryPrincipal();
		List<Role> roleList=new ArrayList<>();
		HashSet<String>  set=new HashSet<>();
		if (roleList!=null){
			for (Role role: roleList
			) {set.add(role.getRoloName());

			}
			info.setRoles(set);
			return info;
		}*/
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
// 这里做登录控制
		AuthenticationInfo info;
		String username=(String) token.getPrincipal();
		String password= String.valueOf((char[]) token.getCredentials());
		User user1=new User();
		user1.setName(username);
		user1.setPassword(password);
		User user =userService.queryUser(user1);
		if(user!=null) {
			info=new SimpleAuthenticationInfo(username, password, "登录成功");
			return info;
		}
		return null;
	}

}
