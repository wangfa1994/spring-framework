package com.wf.model.aop.basecglib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LoggerInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

		System.out.println("进入通用的日志拦截开始");

		Object result = methodProxy.invokeSuper(o, objects);

		System.out.println("进入通用的日志拦截结束");

		return result;
	}
}
