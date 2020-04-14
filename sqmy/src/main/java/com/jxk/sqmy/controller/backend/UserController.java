package com.jxk.sqmy.controller.backend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jxk.sqmy.realm.UserRealm;
import com.jxk.sqmy.util.JiaMi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jxk.sqmy.entity.Role;
import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.AdminService;
import com.jxk.sqmy.service.UserService;

@RestController
@RequestMapping("/backend")
public class UserController {
	@Autowired 
	private AdminService adminService;
	@Autowired
	private UserService userUservice; 
	@RequestMapping(value = "/deluser",method = RequestMethod.POST)
	private Map<String,Object>deluser(HttpServletRequest request,
			@RequestParam("userid")Integer userid){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(adminService.deluser(userid)) {
			modelMap.put("success", true);
		}
		return modelMap;
		
	}
	@RequestMapping(value = "/xiugaiuser", method = RequestMethod.POST)
	private Map<String, Object> xiugaiuser(HttpServletRequest request, @RequestParam("userid") Integer userid,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("job") String job, @RequestParam("type") String type,@RequestParam("roleId") Integer roleId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user=new User(username,password,job,type ,new Role(roleId));
		user.setPassword(JiaMi.MD5Pwd(username,password));
		user.setUserid(userid);
		int x=adminService.xiugaiuser(user);
		if(x>0) {
			modelMap.put("success", true);
		}
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
		realm.clearAllCachedAuthorizationInfo2();
		return modelMap;

	}
	@PostMapping("/addUser")
	public Map<String,Object> addUser(@RequestParam("username") String username,@RequestParam("password")String password,@RequestParam("job")String job,@RequestParam("type")String type,@RequestParam("roleId")Integer roleId){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Role role=new Role(roleId);
		User user=new User(username,password,job,type,role);
		user.setPassword(JiaMi.MD5Pwd(user.getName(),user.getPassword()));
		if(userUservice.insert(user)>0) {
			modelMap.put("success",true);
			return modelMap;
		}	
		return null;	
	}

}
