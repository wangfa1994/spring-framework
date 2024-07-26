package com.wf.xmg.a06injection;

public class User {

	private String name;
	private Integer age;

	public User() {
		System.out.println("进入User类的无参构造方法");
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("进入User类的setName方法");
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		System.out.println("进入user的setAge方法");
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
