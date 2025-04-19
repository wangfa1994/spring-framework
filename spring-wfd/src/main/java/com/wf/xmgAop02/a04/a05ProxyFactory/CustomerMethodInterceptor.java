package com.wf.xmgAop02.a04.a05ProxyFactory;

import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// 这个 MethodInterceptor 属于 aop 联盟定义的 ，不是cglib中的MethodInterceptor
public class CustomerMethodInterceptor implements org.aopalliance.intercept.MethodInterceptor{
	@Nullable
	@Override
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
		System.out.println("进行了方法拦截"+invocation.getMethod());
		return invocation.proceed(); // 进行原方法的执行 MethodInvocation 继承自Join point
	}
}
