package com.sssundaylearn.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springlearn.model.User;
import com.springlearn.service.IUserService;

public class TestSpring {

	ClassPathXmlApplicationContext cs;
	
	@Before
	public void before(){
		cs = new ClassPathXmlApplicationContext("config/beans.xml");
	}
	
	@Test
	public void testAop() {
		
		User u = (User)cs.getBean("user");
		IUserService userService = (IUserService)cs.getBean("userService");
		userService.initUser(u);
		System.out.println(u);
		
	}
	
	
}
