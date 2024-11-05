package com.wf.xmgAop.a08;

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


	@Override
	public int getOrder() {
		return 0;
	}
}
