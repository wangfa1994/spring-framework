package com.wf.model.customerAop.addProxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

public class SimpleLoggingInterceptor implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("[LOG] 调用方法: " + invocation.getMethod().getName() +
				" with args: " + Arrays.toString(invocation.getArguments()));
		Object result = invocation.proceed();
		System.out.println("[LOG] 方法执行完成");
		return result;
	}
}
