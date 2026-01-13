package com.wf.model.enable.ann;

import org.springframework.context.annotation.Bean;

public class HelloConfig {


	@Bean
	public String hello(){
		return "hello";
	}
}
