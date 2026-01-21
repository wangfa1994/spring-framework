package com.wf.model.fullandlite;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentBean {
	@Bean
	public Cat componentCat(){
		return new Cat();
	}

	@Bean
	public Person componentPerson1(){
		Person person = new Person(componentCat());
		return person;
	}

	@Bean
	public Person componentPerson2(){
		Person person = new Person(componentCat());
		return person;
	}
}
