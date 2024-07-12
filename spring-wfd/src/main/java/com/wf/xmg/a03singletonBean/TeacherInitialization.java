package com.wf.xmg.a03singletonBean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TeacherInitialization implements InitializingBean, DisposableBean {

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}



	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct 初始化中...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean#afterPropertiesSet() 初始化中...");
	}
	public void initMethod() {
		System.out.println("自定义初始化方法 initMethod()  初始化中...");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("@PreDestroy  销毁中...");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean#destroy() 销毁中...");
	}

	public void destroyMethod() {
		System.out.println("自定义销毁方法 destroyMethod() 销毁中...");
	}
}
