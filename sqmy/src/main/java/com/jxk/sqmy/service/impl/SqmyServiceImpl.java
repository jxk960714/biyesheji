package com.jxk.sqmy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxk.sqmy.dao.SqmyDao;
import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.Sqmy;
import com.jxk.sqmy.service.SqmyService;
import com.jxk.sqmy.util.PageCalculator;

@Service
public class SqmyServiceImpl implements SqmyService {
	@Autowired
	private SqmyDao sqmyDao;
	@Override
	public List<Category> getCategory() {
		// TODO Auto-generated method stub
		return sqmyDao.getCategory();
	}
	@Override
	public int insertSqmy(Sqmy sqmy) {
		// TODO Auto-generated method stub
		return sqmyDao.insertSqmy(sqmy);
	}
	@Override
	public List<Sqmy> querySqmyListByUserId(Sqmy sqmy, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Sqmy>sqmyList = sqmyDao.querySqmyListByUserId(sqmy, rowIndex, pageSize);
		return sqmyList;
	}

	@Override
	public int querySqmyCount(Sqmy sqmy) {
		// TODO Auto-generated method stub
		return sqmyDao.querySqmyCount(sqmy);
	}

	@Override
	public List<Bsdw> getBsdw() {
		// TODO Auto-generated method stub
		return sqmyDao.getBsdw();
	}

	@Override
	public int delsqmy(int id) {
		// TODO Auto-generated method stub
		return sqmyDao.delsqmy(id);
	}

	
	@Override
	public Sqmy querySqmybyId(int sqmyid) {
		// TODO Auto-generated method stub
		return sqmyDao.querySqmybyId(sqmyid);
	}

	@Override
	public int xiugaisqmy(Sqmy sqmy) {
		// TODO Auto-generated method stub
		return sqmyDao.xiugaisqmy(sqmy);
	}


}
