package com.wf.model.annBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
public class CatBeanFactory {



	@Bean
	public  Cat cat(){
		return new Cat("cat");
	}

	@Bean
	public static Cat catStatic(){
		return new Cat("catStatic");
	}

	/**
	 *
	 *
	 * 这些bean会被处理成 org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.ConfigurationClassBeanDefinition 这个 beanDefinition
	 *
	 */

}
