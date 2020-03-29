package com.jxk.sqmy.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping(value = "backend", method = { RequestMethod.GET,
		RequestMethod.POST })
public class BackEndAdminController {
	@RequestMapping(value = "tongjitu", method = RequestMethod.GET)
	private String tongjitu() {
		return "backend/tongjitu";
	}
	@RequestMapping(value = "categorymanage", method = RequestMethod.GET)
	private String categorymanage() {
		return "backend/categorymanage";
	}
	@RequestMapping(value = "bsdwmanage", method = RequestMethod.GET)
	private String bsdwmanage() {
		return "backend/bsdwmanage";
	}
 @RequestMapping(value = "manage", method = RequestMethod.GET)
	private String manage() {
		return "backend/manage";
	}
 @RequestMapping(value = "usermanage", method = RequestMethod.GET)
	private String usermanage() {
		return "backend/usermanage";
	}
 @RequestMapping(value = "sqmyzhengwen", method = RequestMethod.GET)
	private String sqmyzhengwen() {
		return "backend/zhengwen";
	}
 
}
