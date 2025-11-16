package com.wf.model.customerAop.addProxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class AopProxy implements InvocationHandler {
	private final Object target;
	private final List<MethodInterceptor> interceptors;

	public AopProxy(Object target, List<MethodInterceptor> interceptors) {
		this.target = target;
		this.interceptors = interceptors;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 创建 MethodInvocation 实例
		MethodInvocation invocation = new SimpleMethodInvocation(target, method, args, interceptors);
		// 启动拦截器链
		return invocation.proceed();
	}

	// 工具方法：创建代理对象
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(T target, List<MethodInterceptor> interceptors, Class<T> interfaceType) {
		return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(),
				new Class[]{interfaceType},
				new AopProxy(target, interceptors)
		);
	}
}
