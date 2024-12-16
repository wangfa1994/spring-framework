package com.wf.model.imports.importSelector;

import org.springframework.context.annotation.Bean;

public class SelectImportsBean1 {
	private String name ="selectImportsBean1";

	public SelectImportsBean1() {
		System.out.println("SelectImportsBean1 构造器");
	}

	//当成配置类会进行注入的
	@Bean
	private String cat(){
		return "selectImportsBean1 cat";
	}
}
