package com.wf.model.objectFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class SchoolObjectFactory implements ObjectFactory<School> {
	@Override
	public School getObject() throws BeansException {
		System.out.println("SchoolObjectFactory 进入");
		School school = new School();
		school.setAddress("beijingDongLu");
		school.setName("shiYanXiaoXue");
		return school;
	}
}
