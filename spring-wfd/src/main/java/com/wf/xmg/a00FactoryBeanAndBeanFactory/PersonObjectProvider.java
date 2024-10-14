package com.wf.xmg.a00FactoryBeanAndBeanFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class PersonObjectProvider implements ObjectProvider<Person> {

	@Override
	public Person getObject() throws BeansException {
		Person person = new Person();
		person.setAge(18);
		person.setName("wf");
		return person;
	}

	@Override
	public Person getObject(Object... args) throws BeansException {

		Person person = new Person((String) args[0], (Integer) args[1]);
		return person;
	}

	@Override
	public Person getIfAvailable() throws BeansException {
		return null;
	}

	@Override
	public Person getIfUnique() throws BeansException {
		return null;
	}
}
