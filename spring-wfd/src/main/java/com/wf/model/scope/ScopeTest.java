package com.wf.model.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeTest {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfiguration.class);

		Cat cat = context.getBean("cat", Cat.class);
		System.out.println(cat);

		Cat cat1 = context.getBean("cat", Cat.class);
		System.out.println(cat1);

	}
}
