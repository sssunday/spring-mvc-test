package com.springlearn.service.impl;

import com.springlearn.model.User;
import com.springlearn.service.IUserService;

public class UserServiceImpl implements IUserService{

	@Override
	public User initUser(User u) {
		u.setId(12L);
		u.setName("username");
		u.setAge(20L);
		System.out.println();
		System.out.println("初始化user信息....");
		int a = 1/0;
		return u;
	}
}
