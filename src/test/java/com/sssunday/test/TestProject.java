package com.sssunday.test;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sssunday.utils.RedisUtils;
import com.sssunday.utils.ResourcesUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring.xml")
public class TestProject {

	
	/*@Before
	public void before(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:config/spring.xml");
		context.start();
	}*/
	/*@Test
	public void testResources() {
		ResourcesUtils resourcesUtils = ResourcesUtils.getResouce("properties/constant");
		Logger log = Logger.getLogger(TestProject.class);
		try {
			String s = resourcesUtils.getValue("test1","mike","中国");
			System.out.println(s);
		} catch (UnsupportedEncodingException uee) {
			log.error(uee.getMessage(),uee);
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
	}
	
	@Test
	public void testRedis(){
		
		String s = RedisUtils.get("test","1");
		System.out.println(s);
	}*/
}
