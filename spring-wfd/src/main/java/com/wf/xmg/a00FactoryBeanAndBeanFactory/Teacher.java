package com.wf.xmg.a00FactoryBeanAndBeanFactory;

import org.springframework.stereotype.Component;

@Component
public class Teacher {

	private String name;

	private String job;

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
}
