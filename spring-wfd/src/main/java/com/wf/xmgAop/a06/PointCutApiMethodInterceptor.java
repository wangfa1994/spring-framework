package com.wf.xmgAop.a06;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;

public class PointCutApiMethodInterceptor implements MethodInterceptor {

	@Nullable
	@Override
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		System.out.println("拦截方法：" + method);
		return invocation.proceed();
	}
}
