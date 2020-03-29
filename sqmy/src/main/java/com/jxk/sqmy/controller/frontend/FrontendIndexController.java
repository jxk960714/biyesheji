package com.jxk.sqmy.controller.frontend;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jxk.sqmy.exception.MyException;
import com.jxk.sqmy.util.CodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jxk.sqmy.entity.Role;
import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.UserService;

@RestController
@RequestMapping("/frontend")
public class FrontendIndexController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/denglu", method = RequestMethod.POST)
	public Map<String, Object> Denglu(@RequestParam("name") String username, @RequestParam("password") String password,
			HttpServletRequest request) throws MyException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			resultMap.put("success", false);
			resultMap.put("errMsg", "输入错了验证码");
			return resultMap;
		}
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(username, password);

		User user = new User();
		user.setName(username);
		user.setPassword(password);
		User currentUser = userService.queryUser(user);
		request.getSession().setAttribute("currentUser", currentUser);
		if (currentUser != null) {
			resultMap.put("user", currentUser);
			resultMap.put("success", true);
		} else {
			resultMap.put("success", false);
			resultMap.put("errMsg", "用户名或密码输入错误");
		}
			try {
				//如果subject.login不抛出异常，则证明登录成功
				subject.login(token);
				return resultMap;
			} catch (Exception e) {

				// TODO Auto-generated catch block
				//有异常，则表明密码 或用户名错误
				return resultMap;
			}
		}

	@RequestMapping(value = "/tuichu", method = RequestMethod.POST)
	private Map<String, Object> tuichu(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		request.getSession().removeAttribute("currentUser");
		resultMap.put("success", true);
		return resultMap;

	}

	@RequestMapping(value = "/zhuce", method = RequestMethod.POST)
	public Map<String, Object> zhuce(@RequestParam("name") String name, @RequestParam("password") String password,
			@RequestParam("job") String job, @RequestParam("type") String type, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		User user = new User();
		user.setJob(job);
		user.setName(name);
		user.setType(type);
		user.setPassword(password);
		Role role=new Role();
		role.setRoleId(1);
		user.setRole(role);
		if (userService.insert(user)>0) {
			resultMap.put("success", true);
		}
		return resultMap;

	}

	/*
	 * @RequestMapping(value = "/lianxi", method = RequestMethod.POST) public
	 * Map<String, Object> test3(HttpServletRequest request, @RequestParam("age")
	 * String age,
	 * 
	 * @RequestParam("image") MultipartFile uploadFile) throws Exception,
	 * IOException { Map<String, Object> resultMap = new HashMap<String, Object>();
	 * resultMap.put("success", true); String imgName =
	 * uploadFile.getOriginalFilename(); //获取后缀名 String substring =
	 * imgName.substring(imgName.lastIndexOf(".")); // 保存的文件名 String dFileName =
	 * UUID.randomUUID() + substring; System.out.println(dFileName); String path =
	 * "E:/bysj/image/upload/"; File file = new File(path + dFileName);
	 * uploadFile.transferTo(file); List<String> list = new ArrayList<String>();
	 * list.add("家的味道"); list.add("dadadd"); resultMap.put("list", list); return
	 * resultMap; }
	 */

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String Hello() {
		/* 返回hello */
		return "Hello World";
	}

}
