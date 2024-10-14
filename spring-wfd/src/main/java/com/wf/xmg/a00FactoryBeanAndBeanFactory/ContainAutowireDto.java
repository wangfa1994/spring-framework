package com.wf.xmg.a00FactoryBeanAndBeanFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContainAutowireDto {

	//@Autowired //没有对应的BeanDefinition无法进行注入,spring处理不了ObjectProvider所管理的对象
	private Person person;
	@Autowired
	private PersonObjectProvider personObjectProvider;

	//@Autowired //没有对应的BeanDefinition无法进行注入,spring处理不了ObjectFactory所管理的对象
	private School school;
	@Autowired
	private SchoolObjectFactory schoolObjectFactory;
	@Autowired // Teacher来自FactoryBean,spring针对FactoryBean有特殊逻辑处理，所以能依赖进来
	private Teacher Teacher;
	@Autowired
	private TeacherFactoryBean teacherFactoryBean;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonObjectProvider getPersonObjectProvider() {
		return personObjectProvider;
	}

	public void setPersonObjectProvider(PersonObjectProvider personObjectProvider) {
		this.personObjectProvider = personObjectProvider;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public SchoolObjectFactory getSchoolObjectFactory() {
		return schoolObjectFactory;
	}

	public void setSchoolObjectFactory(SchoolObjectFactory schoolObjectFactory) {
		this.schoolObjectFactory = schoolObjectFactory;
	}

	public Teacher getTeacher() {
		return Teacher;
	}

	public void setTeacher(Teacher teacher) {
		Teacher = teacher;
	}

	public TeacherFactoryBean getTeacherFactoryBean() {
		return teacherFactoryBean;
	}

	public void setTeacherFactoryBean(TeacherFactoryBean teacherFactoryBean) {
		this.teacherFactoryBean = teacherFactoryBean;
	}
}
