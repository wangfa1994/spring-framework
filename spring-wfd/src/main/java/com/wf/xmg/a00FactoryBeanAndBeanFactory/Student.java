package com.wf.xmg.a00FactoryBeanAndBeanFactory;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

	private String name;
	private String grade;

	/*@Autowired  school 无法被注入
	private School school;*/

	@Autowired
	private SchoolObjectFactory schoolObjectFactory;

	@Autowired
	private Teacher teacher;

	@Autowired
	private TeacherFactoryBean teacherFactoryBean;





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

	/*public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}*/

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
