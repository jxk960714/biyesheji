package com.jxk.sqmy.controller.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.service.SqmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jxk.sqmy.entity.Sqmy;
import com.jxk.sqmy.service.AdminService;

@RestController
@RequestMapping("/backend")
public class BackSqmyController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private SqmyService sqmyService;
	@RequestMapping(value = "/getsqmylist", method = RequestMethod.GET)
	private Map<String, Object> getsqmylist(@RequestParam(value = "categoryid",required = false) Integer  categoryid,@RequestParam(value = "title",required = false)String title,@RequestParam("pageIndex") Integer pageIndex) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Sqmy sqmy = new Sqmy();
		sqmy.setTitle(title);
		Category category=new Category();
		category.setId(categoryid);
		sqmy.setCategory(category);
		List<Sqmy> sqmyList = adminService.queryAllSqmyList(sqmy,pageIndex, 8);
		List<Category> categoryList = sqmyService.getCategory();
		Integer count =adminService.querySqmyCount(sqmy);
		int max;
		if(count%8==0&&count!=0) {
			max=count/8;
		}else {
		max = count / 8 + 1;
		}
		modelMap.put("pageIndex", pageIndex);
		modelMap.put("count", count);
		modelMap.put("max", max);
		modelMap.put("sqmyList", sqmyList);
		modelMap.put("categoryList",categoryList);
		modelMap.put("success", true);
		return modelMap;

	}
}
