package com.wf.xmgAop02.a08;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundBeforeXmlConfig {

	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("@Around any public method : " + pjp.getSignature());
		return pjp.proceed();
	}

	// 这里只定义我们的通知方法
	public void beforeAnyPublicMethod() throws Throwable {
		System.out.println("xml  @Before any public method.");
	}
}
