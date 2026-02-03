package com.wf.xmg.a15typeconvert.springregistrar;

import java.util.Properties;

public class User {

	private Long id;

	private String name;

	private String  age;

	private Properties context; // 通过自定义的转换器机型转换


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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Properties getContext() {
		return context;
	}

	public void setContext(Properties context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "User{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   ", age='" + age + '\'' +
			   ", context=" + context +
			   '}';
	}
}
