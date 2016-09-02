package com.sssunday.test;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.sssunday.utils.ResourcesUtils;

public class TestProject {

	
	public static void main(String[] args) {
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
}
