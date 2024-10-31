package com.wf.xmgAop.a00;

public class StudentClassDemo {

	private Integer pid;

	private String name;

	private int age;


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "开始StudentClassDemo{" +
				"pid=" + pid +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
