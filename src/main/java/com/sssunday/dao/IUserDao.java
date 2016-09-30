package com.sssunday.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sssunday.model.User;

public interface IUserDao {

	List<User> queryUser(@Param("id")Integer id);

	int insertUser(Long id);

	int insertUser(User user);
}
