package com.jxk.sqmy.controller.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.AdminService;
import com.jxk.sqmy.service.SqmyService;
@RestController
@RequestMapping("/backend")
public class FirCateController {
	@Autowired
	private SqmyService sqmyService;
	@Autowired
	private AdminService adminService;
	@RequestMapping(value = "/xiugaifristCategory", method = RequestMethod.POST)
	private Map<String, Object> xiugaifirstCategory(HttpServletRequest request,
			@RequestParam("categoryId") String categoryId,
			@RequestParam("categoryName") String categoryName) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Category category=new Category();
		int categoryIdint=Integer.parseInt(categoryId);
		category.setCategoryName(categoryName);
		category.setId(categoryIdint);
		int x=adminService.xiugaiCategory(category);
		if(x>0) {
			modelMap.put("success", true);
		}

		return modelMap;

	}
	@RequestMapping(value = "/getcategory", method = RequestMethod.GET)
	private Map<String, Object> getCategoryandDanwei(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Category> categoryList = sqmyService.getCategory();
	    User user= (User) request.getSession().getAttribute("currentUser");
		List<Bsdw> bsdwList = sqmyService.getBsdw();
		List<User> userList=adminService.queryalluser();
		if(user!=null||categoryList!=null||bsdwList!=null
				||userList!=null) {
			modelMap.put("categoryList", categoryList);
			modelMap.put("bsdwList", bsdwList);
			modelMap.put("admin", user);
			modelMap.put("userList",userList);
			modelMap.put("success", true);
		}
		return modelMap;
	}
	@RequestMapping(value = "/addcategory",method = RequestMethod.POST)
	private Map<String,Object>addCategory(HttpServletRequest request,@RequestParam("categoryName")String categoryName){
		Map<String, Object> modelMap=new HashMap<String,Object>();
		if("".equals(categoryName)) {
			modelMap.put("success", false);	
			return modelMap;
		}
		Category category=new Category();
		category.setCategoryName(categoryName);
		int x=adminService.insertCategory(category);
		if(x>0) {
			modelMap.put("success",true);
		}
		return modelMap;
		
	}
	@RequestMapping(value = "/delCatery", method = RequestMethod.POST)
	private Map<String, Object> delsqmy(HttpServletRequest request, @RequestParam("categoryid") String categoryid) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int categoryidint = Integer.parseInt(categoryid);
		boolean bis=adminService.delcategory(categoryidint);
		System.out.println(bis);
		if (bis) {
			modelMap.put("success", true);
		}
		return modelMap;

	}
}
