package com.wf.model.customerAop.addProxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

public class SimpleMethodInvocation implements MethodInvocation {
	private final Object target;
	private final Method method;
	private final Object[] arguments;
	private final List<MethodInterceptor> interceptors;
	private int currentIndex = -1;

	public SimpleMethodInvocation(Object target, Method method, Object[] arguments, List<MethodInterceptor> interceptors) {
		this.target = target;
		this.method = method;
		this.arguments = arguments;
		this.interceptors = interceptors;
	}

	@Override
	public Method getMethod() { return method; }

	@Override
	public Object[] getArguments() { return arguments; }

	@Override
	public Object proceed() throws Throwable {
		if (currentIndex == interceptors.size() - 1) {
			return method.invoke(target, arguments); // 调用目标
		}
		currentIndex++;
		MethodInterceptor interceptor = interceptors.get(currentIndex);
		return interceptor.invoke(this);
	}

	@Override
	public Object getThis() { return target; }

	@Override
	public AccessibleObject getStaticPart() { return method; }
}