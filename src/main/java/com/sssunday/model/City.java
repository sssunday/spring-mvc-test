package com.sssunday.model;

import java.util.List;

public class City {

	/**
	 * 市id
	 */
	private String id;
	
	/**
	 * 市名
	 */
	private String name;
	
	/**
	 * 下属区
	 */
	private List<Area> area;

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

	public List<Area> getArea() {
		return area;
	}

	public void setArea(List<Area> area) {
		this.area = area;
	}
	
}
