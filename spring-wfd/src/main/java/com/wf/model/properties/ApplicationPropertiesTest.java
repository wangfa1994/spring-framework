package com.wf.model.properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.UnsupportedEncodingException;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationPropertiesTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

		// ApplicationContext等高级容器中存放了一个DefaultListableBeanFactory对象
		System.out.println("hello");
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(PropertiesConfig.class);
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");

		System.out.println(cat.getCategory());

		String property = annotationConfigApplicationContext.getEnvironment().getProperty("hello");
		System.out.println("hello:"+property);


	}



}
