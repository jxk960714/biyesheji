package com.jxk.sqmy.controller.frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.jxk.sqmy.service.SqmyTiJiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.Sqmy;
import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.exception.MyException;
import com.jxk.sqmy.service.SqmyService;
import com.jxk.sqmy.util.FileUtil;

@RestController
@RequestMapping("/frontend")
public class SqmyController {
	@Autowired
	private SqmyService sqmyService;
	@Autowired
	private SqmyTiJiaoService sqmyTiJiaoService;
	/*
	* 得到当前用户，和报送单位列表，种类列表。
	*
	* */
	@GetMapping("/getcategory")
	private Map<String, Object> getCategoryandDanwei(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Category> categoryList = sqmyService.getCategory();
		List<Bsdw> bsdwList = sqmyService.getBsdw();
		User user = (User) request.getSession().getAttribute("currentUser");
		if (categoryList != null || user != null || bsdwList != null) {
			modelMap.put("user", user);
			modelMap.put("bsdwList", bsdwList);
			modelMap.put("categoryList",categoryList);
			modelMap.put("success", true);
		}
		return modelMap;

	}

	@RequestMapping(value = "/insertsqmy", method = RequestMethod.POST)
	private Map<String, Object> insertsqmy(HttpServletRequest request,
			@RequestParam(value = "fj", required = false) MultipartFile fj, @RequestParam("title") String title,
			@RequestParam("categoryId") String categoryId,
			@RequestParam("jjcd") String jjcd,
			@RequestParam("xxly") String xxly, @RequestParam("fyr") String fyr, @RequestParam("bz") String bz,
			@RequestParam("zw") String zw) throws Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int categoryIdint = Integer.parseInt(categoryId);
			int jjcdint;
			if (jjcd.equals("一级")) {
				jjcdint = 1;
			} else if (jjcd.equals("二级")) {
				jjcdint = 2;
			} else {
				jjcdint = 3;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
			String data=formatter.format(new Date(System.currentTimeMillis()));
			String prefix= UUID.randomUUID().toString();
			String keyWord=data+prefix;
			Category category = new Category();
			category.setId(categoryIdint);
			Bsdw bsdw = new Bsdw();
			User user = (User) request.getSession().getAttribute("currentUser");
			Sqmy sqmy = new Sqmy();
			category.setId(categoryIdint);
			sqmy.setCategory(category);
			sqmy.setTitle(title);
			sqmy.setKeyWord(keyWord);
			sqmy.setBsDw(bsdw);
			sqmy.setStatus(0);
			sqmy.setBz(bz);
			if (fj == null) {
				sqmy.setFj(null);
			} else {
				sqmy.setFj(FileUtil.transFile(fj));
			}
			sqmy.setFyr(fyr);
			sqmy.setJjcd(jjcdint);
			sqmy.setXxly(xxly);
			sqmy.setUser(user);
			sqmy.setZw(zw);
			int x = sqmyService.insertSqmy(sqmy);
			if (x > 0) {
				modelMap.put("success", true);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			 throw new MyException( "请输入完整数据");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getsqmylist", method = RequestMethod.GET)
	private Map<String, Object> getsqmylist(HttpServletRequest request, @RequestParam(value = "categoryid",required = false) Integer  categoryid,@RequestParam(value = "title",required = false)String title,@RequestParam("pageIndex") Integer pageIndex) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println(categoryid+"---"+title+"------"+pageIndex);
		Sqmy sqmy = new Sqmy();
		User user = (User) request.getSession().getAttribute("currentUser");
		if (user != null) {
			modelMap.put("user", user);
		}
		sqmy.setUser(user);
		sqmy.setTitle(title);
		Category category=new Category();
		category.setId(categoryid);
		sqmy.setCategory(category);
		List<Sqmy> sqmyList = sqmyService.querySqmyListByUserId(sqmy, pageIndex, 8);
		List<Category> categoryList = sqmyService.getCategory();
		int count = sqmyService.querySqmyCount(sqmy);
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
		modelMap.put("success", true);
		modelMap.put("categoryList",categoryList);
		return modelMap;

	}

	@RequestMapping(value = "/delsqmy", method = RequestMethod.POST)
	private Map<String, Object> delsqmy(HttpServletRequest request, @RequestParam("id") String id) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int idint = Integer.parseInt(id);
		int x = sqmyService.delsqmy(idint);
		if (x > 0) {
			modelMap.put("success", true);
		}
		return modelMap;

	}

	@RequestMapping(value = "/xiugaisqmy", method = RequestMethod.POST)
	private Map<String, Object> xiugaisqmy(HttpServletRequest request,
			@RequestParam(value = "fj", required = false) MultipartFile fj, @RequestParam("title") String title,
			@RequestParam("categoryId")Integer categoryId,
			@RequestParam("jjcd") String jjcd,
			@RequestParam("xxly") String xxly, @RequestParam("fyr") String fyr, @RequestParam("bz") String bz,
			@RequestParam("zw") String zw,  @RequestParam("sqmyid") Integer sqmyid)
			throws IOException, Exception {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int jjcdint;
		if (jjcd.equals("一级")) {
			jjcdint = 1;
		} else if (jjcd.equals("二级")) {
			jjcdint = 2;
		} else {
			jjcdint = 3;
		}

		Sqmy sqmy = new Sqmy();
		Category category = new Category();
	    category.setId(categoryId);
		sqmy.setCategory(category);
		sqmy.setTitle(title);
		sqmy.setBz(bz);
		if (fj != null) {
			sqmy.setFj(FileUtil.transFile(fj));
		}

		sqmy.setFyr(fyr);
		sqmy.setJjcd(jjcdint);
		sqmy.setXxly(xxly);
		sqmy.setZw(zw);
		sqmy.setId(sqmyid);
		int x = sqmyService.xiugaisqmy(sqmy);
		System.out.println(x);
		if (x > 0) {
			modelMap.put("success", true);
		}

		return modelMap;

	}

	@RequestMapping(value = "/getinitinfo", method = RequestMethod.GET)
	private Map<String, Object> getinitinfo(HttpServletRequest request, @RequestParam("sqmyid") Integer sqmyid) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Sqmy sqmy = sqmyService.querySqmybyId(sqmyid);
		if (sqmy != null) {
			modelMap.put("sqmy", sqmy);
			modelMap.put("success", true);
		}
		return modelMap;

	}
	@GetMapping("/tuichu")
	public  boolean tuichu(HttpServletRequest request){
		request.getSession().removeAttribute("currentUser");
		return true;
	}
	@GetMapping("/fabu")
	public  String tijiao(@RequestParam("sqmyid") int sqmyid){
		Date tjtime=new Date();
		if(sqmyTiJiaoService.tijiao(sqmyid,tjtime)){
			return  "提交成功";
		}else {
			return  "已发提交不要重新提交";
		}
	}


}
