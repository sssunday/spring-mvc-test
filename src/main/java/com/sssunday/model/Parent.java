package com.sssunday.model;

import java.io.Serializable;
import java.util.List;

public class Parent implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 7027594368629725258L;

	private Long id;
	
	private String name;
	
	private Double price;
	
	private List<Child> childs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Child> getChilds() {
		return childs;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
	}
	
}
