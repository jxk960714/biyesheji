package com.jxk.sqmy.controller.backend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
			@RequestParam("job") String job, @RequestParam("type") String type) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user=new User();
		user.setJob(job);
		user.setUserid(userid);
		user.setName(username);
		user.setPassword(password);
		user.setType(type);
		int x=adminService.xiugaiuser(user);
		if(x>0) {
			modelMap.put("success", true);
		}

		return modelMap;

	}
	@PostMapping("/addUser")
	public Map<String,Object> addUser(@RequestBody User user){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Role role=new Role();
		role.setRoleId(1);
		user.setRole(role);
		if(userUservice.insert(user)>0) {
			modelMap.put("success",true);
			return modelMap;
		}	
		return null;	
	}

}
