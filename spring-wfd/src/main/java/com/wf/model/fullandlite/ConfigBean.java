package com.wf.model.fullandlite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {


	@Bean
	public Cat configCat(){
		return new Cat();
	}

	@Bean
	public Person configPersonA(){
		Person person = new Person(configCat());
		return person;
	}

	@Bean
	public Person configPersonB(){
		Person person = new Person(configCat());
		return person;
	}
}
