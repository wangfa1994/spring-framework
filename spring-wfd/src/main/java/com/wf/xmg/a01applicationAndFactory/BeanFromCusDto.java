package com.wf.xmg.a01applicationAndFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public class BeanFromCusDto {

	// 非bean
	@Autowired // 这里注入的是我们的上下文中的容器BeanFactory
	private BeanFactory beanFactory;

	// 非bean
	@Autowired // 这里注入的是我们的上下文
	private ApplicationContext applicationContext;

	// 内建bean
	@Autowired
	private Environment environment;


	@Autowired
	private Person person;

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
