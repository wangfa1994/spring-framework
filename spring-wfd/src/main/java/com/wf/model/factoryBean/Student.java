package com.wf.model.factoryBean;


import org.springframework.beans.factory.BeanNameAware;

public class Student implements BeanNameAware {

	private String name;

	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("设置bean的名称"+name);
		this.name = name;
	}
}
