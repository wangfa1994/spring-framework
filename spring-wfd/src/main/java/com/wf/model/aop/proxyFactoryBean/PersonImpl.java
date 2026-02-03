package com.wf.model.aop.proxyFactoryBean;

public class PersonImpl implements Person{

	private String name;
	private String age;
	@Override
	public String getNameInfo() {
		return name;
	}

	@Override
	public String getAgeInfo() {
		return age;
	}



	public void setName(String name) {
		this.name = name;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
