package com.wf.xmg.a00FactoryBeanAndBeanFactory.objectFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactoryAutoContainDto {

	@Autowired
	private SchoolObjectFactory schoolObjectFactory;


	public SchoolObjectFactory getSchoolObjectFactory() {
		return schoolObjectFactory;
	}

	public void setSchoolObjectFactory(SchoolObjectFactory schoolObjectFactory) {
		this.schoolObjectFactory = schoolObjectFactory;
	}
}
