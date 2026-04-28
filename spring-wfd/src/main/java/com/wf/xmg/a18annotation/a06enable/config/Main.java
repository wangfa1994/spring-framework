package com.wf.xmg.a18annotation.a06enable.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableHelloWorld
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		String helloWorld = context.getBean("helloWorld",String.class);

		System.out.println(helloWorld);

	}
}
