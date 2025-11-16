package com.wf.model.profile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.wf.model.profile")
public class ProfileTest {

	/* Profile 条件装配 ，3.1引入， 后来4.0进行重写实现，变成了  @Conditional(ProfileCondition.class)  */
	public static void main(String[] args) {
		// AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProfileTest.class); // 这种报错
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.getEnvironment().addActiveProfile("Java7"); //添加配置的时候，需要未进行初始化的
		applicationContext.register(ProfileTest.class);
		applicationContext.refresh();
		CalculateService bean = applicationContext.getBean(CalculateService.class);
		System.out.println(bean.sum(1,2,3,4,5));
	}
}
