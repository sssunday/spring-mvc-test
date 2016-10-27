package com.sssunday.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sssunday.model.Parent;
import com.sssunday.model.User;

public interface IUserDao {

	List<User> queryUser(@Param("id")Integer id);

	int insertUser(Long id);

	int insertUser(User user);

	List<Parent> testCollection();
	
	int insertTestIndex(Map<String,Object> paramMap);
}
