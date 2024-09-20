package com.wf.xmg.a09beanLifecycle.a05initialize;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

public class UserHolder  implements BeanNameAware, BeanClassLoaderAware , BeanFactoryAware,InitializingBean , EnvironmentAware, ApplicationContextAware {
	// 容器的Aware相关接口
	public String beanName;

	public ClassLoader classLoader;

	public BeanFactory beanFactory;

	// 上下文的Aware相关接口,更多见ApplicationContextAwareProcessor
	public Environment environment;

	public ApplicationContext applicationContext;



	public UserHolder() {
		System.out.println("进入了对应的UserHolder的无参构造器方法");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("进入了InitializingBean的方法afterPropertiesSet");
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("进入了容器中的BeanClassLoaderAware的方法setBeanClassLoader");
		this.classLoader = classLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("进入了容器中的BeanFactoryAware的方法setBeanFactory");
		this.beanFactory = beanFactory;
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("进入了容器中的BeanNameAware的方法setBeanName");
		this.beanName = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("进入了上下文中的ApplicationContextAware的方法setApplicationContext");
		this.applicationContext = applicationContext;
	}

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("进入了上下文中的EnvironmentAware的方法setEnvironment");
		this.environment  = environment;

	}

	public void initMethod() {
		System.out.println("进入了xml中指定的init-method方法中");
	}


	@PostConstruct
	public void postConstruct() {
		System.out.println("进入了@PostConstruct注解的方法中");
	}
}
