package com.wf.model.profile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.AbstractEnvironment;

@ComponentScan("com.wf.model.profile")
public class ProfileTest {

	static {

		//System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,"Java7");

	}

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

	/*
	*  ConfigurableEnvironment API 编码进行 环境配置
	*
	* Profile的处理后来是使用了Condition 条件判断， 这个逻辑主要是在解析配置文件形成BeanDefinition的时候，进行判断是否
	* 可以转换成对应的BeanDefinition。
	* 在ConfigurationClassPostProcess 中 通过ConfigurationClassParser进行解析的时候，
	* 通过 ComponentScanAnnotationParser 进行扫描路径转换，将class文件变成Resource之后，进行获得相关注解元信息，
	* 然后通过 ClassPathScanningCandidateComponentProvider 处理的时候，先进行是否符合我们的注解，然后再进行判断是否条件符合。
	*
	* */
}
