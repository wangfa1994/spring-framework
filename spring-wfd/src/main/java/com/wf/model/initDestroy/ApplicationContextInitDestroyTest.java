package com.wf.model.initDestroy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInitDestroyTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitDestroyConfig.class);

		Student student = applicationContext.getBean("student", Student.class);
		System.out.println(student);
		applicationContext.close();

		/**
		 * @PostConstruct 与 @PreDestroy
		 * 主要是通过后置处理器的postProcessBeforeInitialization方法进行处理的 这个方法是在initializeBean方法中进行处理的。
		 * CommonAnnotationBeanPostProcessor 继承了 InitDestroyAnnotationBeanPostProcessor
		 * 处理注解是在 InitDestroyAnnotationBeanPostProcessor 中进行的。
		 *
		 *
		 */

	}
}
