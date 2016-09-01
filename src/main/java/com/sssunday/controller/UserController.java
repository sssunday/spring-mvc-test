package com.sssunday.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sssunday.model.User;
import com.sssunday.service.IUserService;

@Controller("/")
public class UserController {

	@Autowired
	IUserService userService;
	
	Logger log = Logger.getLogger(UserController.class);
	
	@ResponseBody
	@RequestMapping("/queryUser")
	public List<User> queryUser(Integer id){
		List<User> userList = new ArrayList<User>();
		try {
			userList =  userService.queryUser(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return userList;
	}
	
	@RequestMapping("/test")
	public String testPage(){
		return "test";
	}
}
