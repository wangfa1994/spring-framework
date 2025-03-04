package com.wf.xmgAop02.a04.a04ProxyFactoryBean;

import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CustomerMethodInterceptor implements org.aopalliance.intercept.MethodInterceptor{
	@Nullable
	@Override
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
		System.out.println("进行了方法拦截"+invocation.getMethod());
		return invocation.proceed(); // 进行原方法的执行
	}
}
