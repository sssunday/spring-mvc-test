package com.sssunday.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sssunday.model.User;

import redis.clients.jedis.Jedis;

public class Redis_Test {

	public static void main(String[] args) throws Exception {
		
		Class<?> clz = User.class;
		User user1 = (User)clz.newInstance();
		User user2 = (User)clz.newInstance();
		user2.setId(2L);
		/**
		 * master read-write
		 */
		Jedis jmaster = new Jedis("120.77.66.90", 16379);//host port
		jmaster.auth("sssunday");//auth
		jmaster.set("user1", new ObjectMapper().writeValueAsString(user1)); //objectMapper
		jmaster.set("user2", new Gson().toJson(user2));//gson
		System.out.println();
		System.out.println("jmaster:" + jmaster.get("user1"));
		System.out.println("jamster:" + jmaster.get("user2"));
		System.out.println();
		/**
		 * slave read-only
		 */
		Jedis jslave = new Jedis("120.77.66.90", 26379); 
		jslave.auth("sssunday"); 
		System.out.println("jslave:" + jslave.get("user1"));
		System.out.println("jslave:" + jslave.get("user2"));
	}
}
