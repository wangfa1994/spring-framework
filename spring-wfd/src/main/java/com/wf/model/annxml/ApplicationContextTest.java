package com.wf.model.annxml;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationContextTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AnnXmlConfig.class);
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");

		System.out.println(cat.getCategory());




	}

}
