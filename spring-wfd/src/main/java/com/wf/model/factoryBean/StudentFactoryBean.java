package com.wf.model.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class StudentFactoryBean implements FactoryBean<Student> {

	@Override
	public Student getObject() throws Exception {
		System.out.println("进入到FactoryBean的getObject方法");
		Student student = new Student();
		student.setName("wf");
		student.setAge(18);
		return student;
	}

	@Override
	public Class<?> getObjectType() {
		return Student.class;
	}
}
