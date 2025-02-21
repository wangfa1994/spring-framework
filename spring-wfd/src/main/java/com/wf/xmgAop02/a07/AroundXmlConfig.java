package com.wf.xmgAop02.a07;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Random;

public class AroundXmlConfig {

	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("@Around any public method : " + pjp.getSignature());
		return pjp.proceed();
	}

	// 这里只定义我们的通知方法
	public void beforeAnyPublicMethod() throws Throwable {
		System.out.println("xml  @Before any public method.");
	}
}
