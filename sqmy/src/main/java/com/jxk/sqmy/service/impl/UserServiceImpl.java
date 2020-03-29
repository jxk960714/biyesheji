package com.jxk.sqmy.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxk.sqmy.dao.UserDao;
import com.jxk.sqmy.entity.User;
import com.jxk.sqmy.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;


	@Override
	public User queryById(Integer userid) {
		return null;
	}

	@Override
	public List<User> queryAllByLimit(int offset, int limit) {
		return null;
	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public User update(User user) {
		return null;
	}

	@Override
	public boolean deleteById(Integer userid) {
		return false;
	}

	@Override
	public User queryUser(User user) {
		return userDao.queryUser(user);
	}
}
