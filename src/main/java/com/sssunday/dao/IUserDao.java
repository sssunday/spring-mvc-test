package com.sssunday.dao;

import java.util.List;

import com.sssunday.model.User;

public interface IUserDao {

	List<User> queryUser(Integer id);
}
