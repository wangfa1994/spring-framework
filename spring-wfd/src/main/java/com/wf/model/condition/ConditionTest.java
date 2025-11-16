package com.wf.model.condition;

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
}
