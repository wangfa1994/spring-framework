package com.wf.model.autowire.one;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.stereotype.Component;


public class APerson {
	@Autowired
	private Cat cat;

	/*@Autowired
	private EnvironmentAware environmentAware;

	@Autowired
	private BeanFactoryAware beanFactoryAware;*/


	public APerson() {
		System.out.println("APerson的构造器...");
	}

	public Cat getCat() {
		return cat;
	}
}
