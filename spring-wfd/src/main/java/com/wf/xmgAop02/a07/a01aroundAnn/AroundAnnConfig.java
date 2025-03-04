package com.wf.xmgAop02.a07.a01aroundAnn;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AroundAnnConfig {
	@Pointcut("execution(public * *(..))") // 匹配 Join Point
	private void anyPublicMethod() { // 方法名即 Pointcut 名 对应到AspectJ语法中的定义
		// 这个方法是私有的，不会被执行,Pointcut只是作为一个判断，过滤出对应的连接点(方法)
		System.out.println("@Pointcut at any public method.");
	}



	@Before("anyPublicMethod()")          // Join Point 拦截动作
	public void beforeAnyPublicMethod() throws Throwable {
		System.out.println("@Before any public method.");
	}

	@Around("anyPublicMethod()")         // Join Point 拦截动作  ProceedingJoinPoint 处理中的一个jointPoint ,joint是一个连接点
	public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("@Around any public method.");
		return pjp.proceed(); // around 需要我们手动主动去调用我们的方法 ，这里也可以发现，around是在Before之前进行的，因为，如果没有主动调用，Before是不会运行的
		//return null;
	}
}
