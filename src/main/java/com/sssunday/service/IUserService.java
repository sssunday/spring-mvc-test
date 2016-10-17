package com.sssunday.service;

import java.util.List;

import com.sssunday.model.Parent;
import com.sssunday.model.User;

public interface IUserService {

	List<User> queryUser(Integer id);

	int insertUser(User user);

	List<Parent> testCollection();
	
}
