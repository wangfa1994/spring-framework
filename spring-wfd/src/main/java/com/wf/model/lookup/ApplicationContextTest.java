package com.wf.model.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(LookUpConfig.class);

		// 标记了LookUp,为什么会产生代理对象呢？
		Person person = (Person) annotationConfigApplicationContext.getBean("person");


		System.out.println(person);




	}

}
