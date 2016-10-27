package com.sssunday.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 读取资源文件工具类
 * @ClassName: ResourcesUtils
 * @author: sssunday
 */
public class ResourcesUtils {

	private ResourceBundle resourceBundle;

	Logger log = Logger.getLogger(ResourcesUtils.class);
	private ResourcesUtils(String resource) {
		resourceBundle = ResourceBundle.getBundle(resource);
	}

	/**
	 * @Title: getResouce
	 * @Description: TODO 获取资源
	 * @param resouce
	 * @return: ResourcesUtils
	 */
	public static ResourcesUtils getResouce(String resouce) {
		return new ResourcesUtils(resouce);
	}

	/**
	 * 
	 * @Title: getValue
	 * @Description: 根据Key获取 Value
	 * @param key
	 * @param args
	 *            value中参数序列,参与:{0},{1}....{n}
	 * @return: String
	 * @throws UnsupportedEncodingException 
	 */
	public String getValue(String key, Object... args) throws UnsupportedEncodingException {
		String temp = new String(resourceBundle.getString(key).getBytes("ISO-8859-1"),"UTF-8");
		return MessageFormat.format(temp, args);
	}

	/**
	 * 
	 * @Title: getMap
	 * @Description: 获取所有资源的Map表示
	 * @return: Map<String,String>
	 */
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (String key : resourceBundle.keySet()) {
			map.put(key, resourceBundle.getString(key));
		}
		return map;
	}
}
