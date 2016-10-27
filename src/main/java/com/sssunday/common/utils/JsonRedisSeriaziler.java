package com.sssunday.common.utils;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.SerializationException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * redis相关的json处理工具类 |
 * 普通json可直接用ObjectMapper处理
 * @ClassName: JsonRedisSeriaziler
 * @author: sssunday
 * @date: 2016年9月27日 上午9:18:19
 */
public class JsonRedisSeriaziler {

	public static final String EMPTY_JSON = "{}";

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	protected static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * java-object as json-string
	 * 
	 * @param object
	 * @return
	 */
	public String seriazileAsString(Object object) {
		if (object == null) {
			return EMPTY_JSON;
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception ex) {
			throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}

	/**
	 * json-string to java-object
	 * 
	 * @param str
	 * @return
	 */
	public static <T> T deserializeAsObject(String str, Class<T> clazz) {
		if (str == null || clazz == null) {
			return null;
		}
		try {
			return new Gson().fromJson(str, clazz);
//			return objectMapper.readValue(str, clazz);
		} catch (Exception ex) {
			throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}
}
