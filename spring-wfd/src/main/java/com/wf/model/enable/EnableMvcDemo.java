package com.wf.model.enable;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableCaching
@EnableWebMvc
@EnableAspectJAutoProxy
public class EnableMvcDemo {

	/**
	* spring 的Enable 模块
	*
	*
	*  主要包括两种实现 一种基于注解的
	 *  自定义Enable有两种模式：注解驱动和接口编程。
	 * 无论哪种实现都需要spring 3.0版本的Import注解，
	 * @ImportResource用于导入XML配置文件。@Import用于导入一个或多个配置类。将其注册为Spring Bean。
	 * Import注解在3.0版本中只能支持被Configuration标注的类。
	 * 在3.1版本Import注解的职责范围扩大。还支持用于声明至少一个@Bean方法的类，以及 ImportSelector 和 ImportBeanDefinitionRegistrar的实现类
	 *
	 *  将@Configuration类和@Bean方法声明类归为注解驱动，而ImportSelector 或者 ImportBeanDefinitionRegistrar 的实现类归于接口编程
	*
	 *
	 * ImportSelector,使用spring注解元信息抽象类AnnotationMetadata 作为方法参数，该参数的内容为导入ImportSelector实现的@Configuration类元信息，进而进行动态选择一个或多个配置类进行导入
	 *
	 * ImportBeanDefinitionRegistrar 除了注解的类元信息外，还将Bean定义的注册交给开发人员处理。
	 *
	* */
}
