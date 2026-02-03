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

		/*
		* 通过Import导入的类不会被spring容器管理，在解析的过程中只会创建起实例，然后回调对应的方法
		* */

	}

}
