package com.wf.xmg.a00FactoryBeanAndBeanFactory.factoryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoContainDto {

	@Autowired
	private Teacher teacher;

	@Autowired
	private TeacherFactoryBean teacherFactoryBean;


	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeacherFactoryBean getTeacherFactoryBean() {
		return teacherFactoryBean;
	}

	public void setTeacherFactoryBean(TeacherFactoryBean teacherFactoryBean) {
		this.teacherFactoryBean = teacherFactoryBean;
	}
}
