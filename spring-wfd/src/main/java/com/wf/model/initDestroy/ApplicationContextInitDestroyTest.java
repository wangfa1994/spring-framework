package com.wf.model.initDestroy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInitDestroyTest {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitDestroyConfig.class);

		Student student = applicationContext.getBean("student", Student.class);
		System.out.println(student);

	}
}
