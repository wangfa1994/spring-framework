package com.wf.xmg.a00FactoryBeanAndBeanFactory.objectProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectProviderContainDto {

	@Autowired
	private PersonObjectProvider personObjectProvider;

	public PersonObjectProvider getPersonObjectProvider() {
		return personObjectProvider;
	}

	public void setPersonObjectProvider(PersonObjectProvider personObjectProvider) {
		this.personObjectProvider = personObjectProvider;
	}
}
