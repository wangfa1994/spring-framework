package com.wf.model.aop.proxyFactoryBean;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MyAdvisor implements Advisor {

	private String content;
	@Override
	public Advice getAdvice() {
		return new MethodBeforeAdvice(){

			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				String name = method.getName();
				if(name.contains("Age")){
					System.out.println("into myAdvisor: " + name);
				}

			}
		};
	}

	@Override
	public boolean isPerInstance() {
		return false;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
