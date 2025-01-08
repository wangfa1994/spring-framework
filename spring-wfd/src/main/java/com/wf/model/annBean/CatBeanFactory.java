package com.wf.model.annBean;

import org.springframework.beans.factory.annotation.Qualifier;
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


	// 带参数的
	/*@Bean
	public ParameterBean parameterBean(Cat cat){ // 会解析参数，根据参数进行依赖查找
		ParameterBean bean = new ParameterBean();
		bean.setCat(cat);
		return bean;
	}*/

	@Bean
	public ParameterBean parameterBean(@Qualifier("catStatic") Cat cat){ // 会解析参数，根据参数进行依赖查找 ,可以通过Qualifier进行指定
		ParameterBean bean = new ParameterBean();
		bean.setCat(cat);
		return bean;
	}

	/**
	 *
	 *
	 * 这些bean会被处理成 org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.ConfigurationClassBeanDefinition 这个 beanDefinition
	 *
	 */

}
