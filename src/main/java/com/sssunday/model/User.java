package com.sssunday.model;

/**
 * 用户测试类
 * @ClassName: User
 * @author: sssunday
 * @date: 2016年9月2日 上午11:46:00
 */
public class User {

	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 年龄
	 */
	private Long age;
	
	public Long getId() throws Exception{
		return id;
	}

	public void setId(Long id) throws Exception{
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
