package com.wf.model.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Set;

// 使用@Qualifier注解进行逻辑分组

@ComponentScan("com.wf.model.qualifier")
public class QualifierGroupTest {

	/*@Autowired
	@Qualifier(value = "shangHaiProductService")
	private ProductService productService;


	@Autowired
	private Set<ProductService> productServiceSet;*/


	@Autowired
	@Qualifier
	private Set<ProductService> productServiceSetQualifier;



	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(QualifierGroupTest.class);

		QualifierGroupTest qualifierGroupTest = (QualifierGroupTest)applicationContext.getBean("qualifierGroupTest");

		//ProductService productService1 = qualifierGroupTest.productService;
		//System.out.println(productService1);


		//Set<ProductService> productServiceSet1 = qualifierGroupTest.productServiceSet;
		//System.out.println(productServiceSet1);


		Set<ProductService> productServiceSetQualifier = qualifierGroupTest.productServiceSetQualifier;



		System.out.println(productServiceSetQualifier);


	}




}
