package com.wf.xmg.a00FactoryBeanAndBeanFactory;

import org.springframework.beans.factory.BeanNameAware;

public class Teacher  implements BeanNameAware {

	private String name;

	private String job;

	private String beanName="";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	// BeanNameAware 接口的回调
	public void setBeanName(String name) {
		System.out.println("====BeanNameAware接口的回调方法设置对应的beanName"+name);
		beanName  = name;
	}


	@Override
	public String toString() {
		return "Teacher{" +
				"name='" + name + '\'' +
				", job='" + job + '\'' +
				", beanName='" + beanName + '\'' +
				'}';
	}
}