package com.sssunday.controller;

import org.apache.log4j.Logger;

import com.sssunday.common.utils.ResourcesUtils;

public class MainTest {

	public static void main(String[] args) {
		Logger log = Logger.getLogger(MainTest.class);
		try {
			ResourcesUtils resourcesUtils = ResourcesUtils.getResouce("properties/constant");
			String s = resourcesUtils.getValue("propFrom");
			System.out.println(s);
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
}
