package com.wf.xmg.a00FactoryBeanAndBeanFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class TeacherFactoryBean  implements FactoryBean<Teacher> {
	@Override
	public Teacher getObject() throws Exception {
		System.out.println("TeacherFactoryBean 进入");
		Teacher teacher = new Teacher();
		teacher.setName("xiaowang");
		teacher.setJob("Math");
		// 这里不进行beanName设置，确定是否可以通过aware的回调进行设置
		return teacher;
	}

	@Override
	public Class<?> getObjectType() {
		return Teacher.class;
	}

	@Override
	public boolean isSingleton() {
		return FactoryBean.super.isSingleton();
	}
}
