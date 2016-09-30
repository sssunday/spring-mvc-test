package com.sssunday.common.utils;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis工具类
 * @ClassName: RedisUtils
 * @author: sssunday
 * @date: 2016年9月27日 上午9:17:49
 */
@Component
public class RedisUtils {

private static Logger log = Logger.getLogger(RedisUtils.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private static JsonRedisSeriaziler seriaziler = new JsonRedisSeriaziler();
	
	private static RedisUtils redisUtils;
	
	@PostConstruct
	public void init(){
		redisUtils = this;
		redisUtils.stringRedisTemplate = stringRedisTemplate;
	}
	
	
	
	
	
	private static String bulidKey(String type,String key){
		return type + key;
	}
	
	/**
	 * 添加到redis
	 * @param type
	 * @param key
	 * @param value
	 */
	public static void add(String type,String key,Object value){
		if(StringUtils.isEmpty(key) || StringUtils.isEmpty(type) || value == null ){
			return;
		}
		ValueOperations<String, String> operations = redisUtils.stringRedisTemplate.opsForValue();
		operations.set(bulidKey(type, key), seriaziler.seriazileAsString(value));
		log.info(type + key + "-->" + value);
	}
	/**
	 * 添加数据到redis,并设置失效时间
	 * @param type
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public static void add(String type,String key,Object value,Long timeout){
		if(StringUtils.isEmpty(key) || StringUtils.isEmpty(type) || value == null ){
			return;
		}
		ValueOperations<String, String> operations = redisUtils.stringRedisTemplate.opsForValue();
		operations.set(bulidKey(type, key), seriaziler.seriazileAsString(value),timeout);
		log.info(type + key + "-->" + value);
	}
	
	
	
	/**
	 * 获取redis中的缓存
	 * @param type
	 * @param key
	 * @return
	 */
	public static String get(String type,String key){
		if(StringUtils.isEmpty(key) || StringUtils.isEmpty(type)){
			return null;
		}
		ValueOperations<String, String> operations = redisUtils.stringRedisTemplate.opsForValue();
		return operations.get(bulidKey(type, key));
	}
	
	/**
	 * 删除redis中的缓存
	 * @param type
	 * @param key
	 */
	public static void delete(String type,String key) {
		if(StringUtils.isEmpty(key) || StringUtils.isEmpty(type)){
			return;
		}
		ValueOperations<String, String> operations = redisUtils.stringRedisTemplate.opsForValue();
		RedisOperations<String, String> redisOperations = operations.getOperations();
		redisOperations.delete(bulidKey(type, key));
	}
	
	/**
	 * 模糊匹配删除redis中的缓存
	 * @param type
	 * @param key
	 */
	public static void deletePattern(String type,String pattern) {
		if(StringUtils.isEmpty(pattern) || StringUtils.isEmpty(type)){
			return;
		}
		Set<String> keys = redisUtils.stringRedisTemplate.keys(bulidKey(type,pattern));
		if(!keys.isEmpty()){
			redisUtils.stringRedisTemplate.delete(keys);
		}
	}
}
