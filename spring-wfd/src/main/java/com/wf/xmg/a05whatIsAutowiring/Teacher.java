package com.wf.xmg.a05whatIsAutowiring;


public class Teacher {

	private String name;

	private Student student;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("进入Teacher的setName方法");
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		// 方法名必须是可写的，和java的beanInfo体系一致，不能是其他的方法名
		System.out.println("进入Teacher的setStudent方法");
		this.student = student;

	}


	@Override
	public String toString() {
		return "Teacher{" +
				"name='" + name + '\'' +
				", student=" + student +
				'}';
	}
}
