package com.wf.xmgAop02.a04.a05ProxyFactory;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class CustomerMethodBeforeInterceptor implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("进入了before方法中");
	}
}
