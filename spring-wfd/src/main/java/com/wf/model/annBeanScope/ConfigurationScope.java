package com.wf.model.annBeanScope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationScope {

	@Bean
	public Cat catConfigurationScope(){
		Cat cat = new Cat();
		cat.setCategory(cateC());
		return cat;
	}

	@Bean
	public String cateC(){
		String s = new String("hi" + Math.random());
		return s;
	}
}
