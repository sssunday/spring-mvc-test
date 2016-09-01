package com.sssunday.model;

public class User {

	private Long id;
	
	private String name;
	
	private Long age;
	
	private String name_back;

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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	
	public String getName_back() {
		return name_back;
	}

	public void setName_back(String name_back) {
		this.name_back = name_back;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", name_back=" + name_back + "]";
	}

}
