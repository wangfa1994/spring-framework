package com.wf.xmg.a17event.a05publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;

public class ApplicationEventMulticasterDemo {

	@Autowired
	private ApplicationEventMulticaster applicationEventMulticaster;


	// ApplicationEventMulticaster

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationEventMulticasterDemo.class);


		ApplicationEventMulticasterDemo bean = context.getBean(ApplicationEventMulticasterDemo.class);

		System.out.println("applicationEventMulticaster: "+bean.applicationEventMulticaster);


	}
}
