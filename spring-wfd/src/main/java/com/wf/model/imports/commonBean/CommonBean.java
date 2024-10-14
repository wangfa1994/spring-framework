package com.wf.model.imports.commonBean;

import org.springframework.context.annotation.Bean;

public class CommonBean {
	private String name ="zhangsan";

	public CommonBean() {
		System.out.println("CommonBean 构造器...");
	}


	// Import导入的bean，相当于一个配置类，可以进行其他配置
	@Bean
	public AnotherBean anotherBean(){
		return new AnotherBean();
	}
}
