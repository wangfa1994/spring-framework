package com.wf.xmgAop02.a08.a01aspjectAroundAnn;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

@Aspect
public class AroundBeforeAnnConfig2 implements Ordered {

	@Pointcut("execution(public * *(..))") // 匹配 Join Point
	private void anyPublicMethod() {
	}

	@Before("anyPublicMethod()")          // Join Point 拦截动作
	public void beforeAnyPublicMethod() throws Throwable {
		System.out.println("@Before 2 any public method.");
	}


	@Override // 实现ordered 设置优先级，没有设置的是最低优先级的
	public int getOrder() {
//		return  HIGHEST_PRECEDENCE;
		return 0;
	}
}
