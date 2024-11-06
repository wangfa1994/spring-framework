package com.wf.xmgAop03.a05;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AfterAdviceConfig {
	@Pointcut("execution(public * *(..))") // 匹配 Join Point
	private void anyPublicMethod() { // 方法名即 Pointcut 名
		System.out.println("@Pointcut at any public method.");
	}

	@After("anyPublicMethod()") // // Join Point 拦截动作
	public void finalizeAnyPublicMethod() {
		System.out.println("@After any public method.");
	}

	@AfterReturning("anyPublicMethod()")
	public void afterAnyPublicMethod() {
		System.out.println("@AfterReturning any public method.");
	}

	@AfterThrowing("anyPublicMethod()")
	public void afterThrowingAnyPublicMethod() {
		System.out.println("@AfterThrowing any public method");
	}


}
