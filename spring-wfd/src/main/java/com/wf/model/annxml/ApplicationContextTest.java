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


		//注解 和xml配合使用的时候，使用的是注解 ImportResource ,在工厂后置处理器的时候ConfigurationClassPostProcessor，会进行importResource资源的解析变成对应的BeanDefinition

	}

}
