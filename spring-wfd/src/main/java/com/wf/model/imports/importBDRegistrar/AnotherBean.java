package com.wf.model.imports.importBDRegistrar;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/*@Component
@Import(AnotherMyBeanDefinitionRegisterBean.class)*/
public class AnotherBean {

	private String name = "anotherBean";

	public AnotherBean() {
		System.out.println("AnotherBean 构造器...");
	}
}
