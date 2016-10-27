package com.sssunday.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sssunday.common.utils.ReadJsonFile;
import com.sssunday.common.utils.ResourcesUtils;

public class UnitTestBySpringTest {

	//@Test
	public void testResources() {
		Logger log = Logger.getLogger(TestProject.class);
		try {
			ResourcesUtils resourcesUtils = ResourcesUtils.getResouce("properties/constant");
			String s = resourcesUtils.getValue("propFrom");
			System.out.println(s);
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
	}
	@Test
	public void testJsonRead(){
		
	}
}
