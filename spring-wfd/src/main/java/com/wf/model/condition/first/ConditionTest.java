package com.wf.model.condition.first;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/*
*  @Conditional注解的使用
*  可以模仿@Profile，在4.0，改为了Condition实现
* */
public class ConditionTest {

	@Bean
	@ConditionalOnSystemProperty(name = "user.name", value = "Administrator") // 通过条件配置进行装配
	public String helloWorld() {
		return "Hello,World 小马哥";
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionTest.class);
		String bean = applicationContext.getBean("helloWorld",String.class);
		System.out.println(bean);
	}

	/*
	* spring 的条件装配
	* 从 Spring Framework 3.1 开始，允许在 Bean 装配时增加前置条件判断
	*
	*
	* Condition的过滤处理在BeanDefinition阶段进行处理的，如果不符合的话，直接就不产生对应的BeanDefinition了
	*
	*
	* */
}
