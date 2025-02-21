package com.wf.xmgAop02.a09;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Random;

public class AfterXmlConfig {

	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		/*Random random = new Random(); // 触发异常通知
		if (random.nextBoolean()) {
			throw new RuntimeException("For Purpose from XML configuration.");
		}*/
		System.out.println("@Around any public method : " + pjp.getSignature());
		return pjp.proceed();
	}

	public void beforeAnyPublicMethod() {
		System.out.println("@Before any public method.");
	}

	public void finalizeAnyPublicMethod() {
		System.out.println("@After any public method.");
	}

	public void afterAnyPublicMethod() {
		System.out.println("@AfterReturning any public method.");
	}

	public void afterThrowingAnyPublicMethod() {
		System.out.println("@AfterThrowing any public method.");
	}
}
