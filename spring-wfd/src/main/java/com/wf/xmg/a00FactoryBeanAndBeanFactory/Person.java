package com.wf.xmg.a00FactoryBeanAndBeanFactory;

public class Person {
	private String name;
	private Integer age;

	public Person() {
		System.out.println("Person无参构造函数");
	}

	public Person(String name, Integer age) {
		System.out.println("Person有参构造函数");
		this.name = name;
		this.age = age;
	}

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
}
