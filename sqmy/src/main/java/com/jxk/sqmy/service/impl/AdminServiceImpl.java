package com.jxk.sqmy.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.jxk.sqmy.dao.SqmyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxk.sqmy.dao.AdminDao;
import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.Sqmy;
import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.AdminService;
import com.jxk.sqmy.util.PageCalculator;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private SqmyDao sqmyDao;
	@Override
	public List<Sqmy> queryAllSqmyList(Sqmy sqmy,int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Sqmy>sqmyList = adminDao.queryAllSqmyList( sqmy, rowIndex, pageSize);
		return sqmyList;
	}

	@Override
	public int querySqmyCount(Sqmy sqmy) {
		// TODO Auto-generated method stub
		return adminDao.querySqmyCount(sqmy);
	}

	@Override
	public int tg(int sqmyId) {
		// TODO Auto-generated method stub
		return adminDao.tg(sqmyId);
	}

	@Override
	public int btg(int sqmyId) {
		// TODO Auto-generated method stub
		return adminDao.btg(sqmyId);
	}

	@Override
	public int insertCategory(Category Category) {
		// TODO Auto-generated method stub
		return adminDao.insertCategory(Category);
	}

	@Override
	public boolean delcategory(int categoryId) {
		// TODO Auto-generated method stub
				return adminDao.delcategory(categoryId);
	}

	@Override
	public List<User> queryalluser() {
		// TODO Auto-generated method stub
		return adminDao.queryalluser();
	}
	@Override
	public int xiugaiCategory(Category Category) {
		// TODO Auto-generated method stub
		return adminDao.xiugaiCategory(Category);
	}

	@Override
	public int xiugaibsdw(Bsdw bsdw) {
		// TODO Auto-generated method stub
		return adminDao.xiugaibsdw(bsdw);
	}

	@Override
	public int xiugaiuser(User user) {
		// TODO Auto-generated method stub
		return adminDao.xiugaiuser(user);
	}

	@Override
	public int inertbsdw(Bsdw bsdw) {
		// TODO Auto-generated method stub
		return adminDao.inertbsdw(bsdw);
	}

	@Override
	@Transactional
	public boolean deluser(int userId) {
		// TODO Auto-generated method stub
		if(adminDao.delSqmyByUserId(userId)) {
			return adminDao.deluser(userId);
		}else {
		return adminDao.deluser(userId);
		}
	}

	@Override
	public List<Integer> getSqmyCountByUserAndAtatus() {
		List<User> userList=adminDao.queryalluser();
		List<Integer> countList=new ArrayList<>();
		for (int i = 0; i <userList.size() ; i++) {
			int number=adminDao.querySqmyByUserandStatus(userList.get(i));
			countList.add(number);
		}
		return countList;
	}
	@Override
	public  List<Integer> getSqmyCountByCategoryAndStatus(){
		List<Category> categoryList=sqmyDao.getCategory();
		List<Integer> categoryCountList=new ArrayList<>();
		for (int i = 0; i <categoryList.size() ; i++) {
			int number=adminDao.querySqmyByCategoryAndStatus(categoryList.get(i));
			categoryCountList.add(number);
		}
		return  categoryCountList;

	}


}
