package com.wf.model.enable.ann;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.wf.model.enable.ann")
@EnableHello
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		Object hello = context.getBean("hello");
		System.out.println(hello);

	}
}
