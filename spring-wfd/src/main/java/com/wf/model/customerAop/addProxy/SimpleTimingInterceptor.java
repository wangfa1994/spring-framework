package com.wf.model.customerAop.addProxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleTimingInterceptor implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = invocation.proceed();
		long end = System.currentTimeMillis();
		System.out.println("[TIME] 执行耗时: " + (end - start) + "ms");
		return result;
	}
}
