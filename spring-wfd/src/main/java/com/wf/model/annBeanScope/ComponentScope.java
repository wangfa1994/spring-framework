package com.wf.model.annBeanScope;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ComponentScope {
	@Bean
	public Cat catComponentScope(){
		Cat cat = new Cat();
		cat.setCategory(cateS());
		return cat;
	}

	@Bean
	public String cateS(){
		String s = new String("hi" + Math.random());
		return s;
	}
}
