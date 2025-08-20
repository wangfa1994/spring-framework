package com.wf.xmg.a00FactoryBeanAndBeanFactory.beanFactory;

import org.springframework.stereotype.Component;

@Component
public class Student {

	private String name;
	private String grade;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", grade='" + grade + '\'' +
				'}';
	}
}
