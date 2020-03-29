package com.jxk.sqmy.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping(value = "frontend", method = { RequestMethod.GET,
		RequestMethod.POST })
public class AdminController {
	@RequestMapping(value = "index", method = RequestMethod.GET)
	private String index() {
		return "index";
	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	private String login() {
		return "frontend/login";
	}
	@RequestMapping(value = "register", method = RequestMethod.GET)
	private String zhuce() {
		return "frontend/zhuce";
	}
	@RequestMapping(value = "sqmyadd", method = RequestMethod.GET)
	private String detail() {
		return "frontend/sqmyadd";
	}
	@RequestMapping(value = "sqmylist", method = RequestMethod.GET)
	private String sqmylist() {
		return "frontend/sqmylist";
	}
	@RequestMapping(value = "sqmyedit", method = RequestMethod.GET)
	private String sqmyedit() {
		return "frontend/sqmyedit";
	}
	@RequestMapping(value = "sqmyzhengwen", method = RequestMethod.GET)
	private String sqmyzhengwen() {
		return "frontend/zhengwen";
	}
	
}
