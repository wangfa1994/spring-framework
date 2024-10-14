package com.wf.model.imports.importBDRegistrar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(MyBeanDefinitionRegisterBean.class)
@ComponentScan(basePackages = "com.wf.model.imports.importBDRegistrar")
public class ApplicationContextBDRegisterTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContextBDRegisterTest.class);

		System.out.println("hello word");

	}

}
