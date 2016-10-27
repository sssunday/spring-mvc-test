package com.sssunday.model;

import java.util.List;

/**
 * 地址信息对象实体
 * @ClassName: Province
 * @author: sssunday
 * @date: 2016年9月28日 上午9:58:50
 */
public class Province {

	/**
	 * 省id
	 */
	private String id;
	
	/**
	 * 省名
	 */
	private String name;
	
	/**
	 * 下属城市
	 */
	private List<City> city;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}
	
}
