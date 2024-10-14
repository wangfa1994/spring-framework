package com.wf.model.aop.basejdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggerAspect implements InvocationHandler {

	private final Object target;

	public LoggerAspect(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("进入通用的日志拦截开始");

		Object result = method.invoke(target, args);

		System.out.println("进入通用的日志拦截结束");

		return result;
	}
}
