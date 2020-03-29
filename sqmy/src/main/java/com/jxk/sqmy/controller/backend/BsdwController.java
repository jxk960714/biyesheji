package com.jxk.sqmy.controller.backend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jxk.sqmy.dao.BsdwDao;
import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.service.AdminService;

@RestController
@RequestMapping("/backend")
public class BsdwController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private BsdwDao bsdwDao;
	@RequestMapping(value = "/xiugaibsdw", method = RequestMethod.POST)
	private Map<String, Object> xiugaibsdw(HttpServletRequest request, @RequestParam("bsdwId") String bsdwId,
			@RequestParam("bsdwname") String bsdwname, @RequestParam("bsdwphone") String bsdwphone,
			@RequestParam("bsdwnum") String bsdwnum, @RequestParam("bsdwemail") String bsdwemail) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int bsdwIdint=Integer.parseInt(bsdwId);
		int number=Integer.parseInt(bsdwnum);
		Bsdw bsdw=new Bsdw();
		bsdw.setEmail(bsdwemail);
		bsdw.setId(bsdwIdint);
		bsdw.setName(bsdwname);
		bsdw.setNumber(number);
		bsdw.setPhone(bsdwphone);
		int x=adminService.xiugaibsdw(bsdw);
		if(x>0) {
			modelMap.put("success", true);
		}

		return modelMap;

	}
	@RequestMapping(value = "/addbsdw",method = RequestMethod.POST)
	private Map<String,Object>addbsdw(HttpServletRequest request,@RequestParam("name")String name,
			@RequestParam("number")String number, 
			@RequestParam("phone")String phone ,
			@RequestParam("email")String email){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int bsdwnum=Integer.parseInt(number);
		Bsdw bsdw=new Bsdw();
		bsdw.setEmail(email);
		bsdw.setName(name);
		bsdw.setNumber(bsdwnum);;
		bsdw.setPhone(phone);
		int x=adminService.inertbsdw(bsdw);
		if(x>0) {
			modelMap.put("success", true);
		}

		return modelMap;
		
	}
	@PostMapping("/delbsdw")
	public Map<String,Object> delbsdw(int bsdwId){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(bsdwDao.delbsdw(bsdwId)) {
			modelMap.put("success", true);
			return  modelMap;
		}	
		return null;	
	}

}
