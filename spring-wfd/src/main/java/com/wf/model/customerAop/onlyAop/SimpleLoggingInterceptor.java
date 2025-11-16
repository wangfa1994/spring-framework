package com.wf.model.customerAop.onlyAop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleLoggingInterceptor implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("Before: " + invocation.getMethod().getName());
		Object result = invocation.proceed(); // 继续调用
		System.out.println("After: " + invocation.getMethod().getName());
		return result;
	}
}
