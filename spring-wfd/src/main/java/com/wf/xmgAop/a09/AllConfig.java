package com.wf.xmgAop.a09;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Random;

@Aspect
public class AllConfig {
	@Pointcut("execution(public * *(..))") // 匹配 Join Point
	private void anyPublicMethod() { // 方法名即 Pointcut 名
		System.out.println("@Pointcut at any public method.");
	}

	@Around("anyPublicMethod()")         // Join Point 拦截动作
	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("@Around any public method.");
		return pjp.proceed();
	}

	@Before("anyPublicMethod()")          // Join Point 拦截动作
	public void beforeAnyPublicMethod() throws Throwable {
		System.out.println("@Before any public method.");
	}


	@After("anyPublicMethod()")
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
