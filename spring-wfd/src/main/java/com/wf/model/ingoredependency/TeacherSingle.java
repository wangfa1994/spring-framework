package com.wf.model.ingoredependency;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class TeacherSingle {

	private ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	 public void setApplicationContext(ApplicationContext applicationContext)  throws BeansException{
		this.applicationContext = applicationContext;
	}


	@Override
	public String toString() {
		return "TeacherSingle{" +
				"applicationContext=" + applicationContext +
				'}';
	}

	public TeacherSingle() {
	}

	public TeacherSingle(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
