package com.wf.model.annxml.ann;

import org.springframework.stereotype.Component;

@Component
public class DogAnn {

	private String  name;
	private String age;

	public DogAnn() {
		System.out.println("注解中的dog无参构造器..");
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
}
