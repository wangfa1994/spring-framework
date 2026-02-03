package com.wf.model.aop.proxyFactoryBean;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("model/aop/proxyFactoryBean.xml");
		Person person = (Person) context.getBean("person");
		System.out.println(person.getAgeInfo());
		System.out.println(person.getNameInfo());
		System.out.println(person.toString());

		ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) context.getBean("&person");
		System.out.println(proxyFactoryBean);

	}
}
