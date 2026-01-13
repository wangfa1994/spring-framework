package com.wf.model.stereotype;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

// spring的模式注解 stereotype Annotation

@ComponentScan("com.wf.model.stereotype")
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext  = new AnnotationConfigApplicationContext(Main.class);

		NameRepository nameRepository = (NameRepository)annotationConfigApplicationContext.getBean("nameRepository");
		List<String> all = nameRepository.findAll();
		System.out.println(all);
	}

	/*
	*
	*  ClassPathBeanDefinitionScanner
	*
	* ClassPathScanningCandidateComponentProvider
	*
	* AnnotationTypeFilter
	*
	*
	* */
}
