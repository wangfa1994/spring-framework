package com.wf.xmgAop.a02.customer;

import com.wf.xmgAop.a01.jdk.DefaultEchoService;
import com.wf.xmgAop.a01.jdk.EchoService;
import com.wf.xmgAop.a02.customer.interceptor.AfterReturnInterceptor;
import com.wf.xmgAop.a02.customer.interceptor.BeforeInterceptor;
import com.wf.xmgAop.a02.customer.interceptor.Interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class MyInvocationTest {

	public static void main(String[] args) {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		MyInvocationHandler myInvocationHandler = new MyInvocationHandler();

		// 前置拦截器
		BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
			@Override
			public Object before(Object proxy, Method method, Object[] args) {
				System.out.println("进入了前置拦截器");
				return System.currentTimeMillis();
			}
		};

		// 方法执行后置拦截器
		AfterReturnInterceptor afterReturnInterceptor = new AfterReturnInterceptor() {
			@Override
			public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
				System.out.println("进入了方法执行后置拦截器");
				return System.currentTimeMillis();
			}
		};
		List<Interceptor> interceptors = new ArrayList<>(); interceptors.add(beforeInterceptor);interceptors.add(afterReturnInterceptor);
		myInvocationHandler.setInterceptors(interceptors);
		myInvocationHandler.setTarget(new DefaultEchoService());



		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class},myInvocationHandler);

		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello,World");

	}
}
