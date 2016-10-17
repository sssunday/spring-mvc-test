package com.sssunday.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sssunday.dao.IUserDao;
import com.sssunday.model.Parent;
import com.sssunday.model.User;
import com.sssunday.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserDao userDao;
	
	
	public List<User> queryUser(Integer id) {
		return userDao.queryUser(id);
	}


	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}


	@Override
	public List<Parent> testCollection() {
		return userDao.testCollection();
	}
}
