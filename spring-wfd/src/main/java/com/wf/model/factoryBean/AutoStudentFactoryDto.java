package com.wf.model.factoryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AutoStudentFactoryDto {


	@Autowired
	private Student student;

	@Autowired
	private StudentFactoryBean studentFactoryBean;



	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentFactoryBean getStudentFactoryBean() {
		return studentFactoryBean;
	}

	public void setStudentFactoryBean(StudentFactoryBean studentFactoryBean) {
		this.studentFactoryBean = studentFactoryBean;
	}

	@Override
	public String toString() {
		return "AutoStudentFactoryDto{" +
				"student=" + student +"\n"+
				", studentFactoryBean=" + studentFactoryBean +
				'}';
	}
}
