package com.wf.xmgAop.a02;

import com.wf.xmgAop.a01.jdk.DefaultEchoService;
import com.wf.xmgAop.a01.jdk.EchoService;
import com.wf.xmgAop.a02.interceptor.AfterReturnInterceptor;
import com.wf.xmgAop.a02.interceptor.BeforeInterceptor;
import com.wf.xmgAop.a02.interceptor.ExceptionInterceptor;
import com.wf.xmgAop.a02.interceptor.FinallyInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 判断模式之后的拦截模式
 *
 * 三种拦截模式
 *  通过提取对应的抽象方法，可以定义出整体模板，然后通过不同的实现进行切换到不同的逻辑，
 *  进行抽象实现
 */


public class AopInterceptorTest {

	public static void main(String[] args) {
		// 前置模式 + 后置模式
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					// 前置拦截器
					BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
						@Override
						public Object before(Object proxy, Method method, Object[] args) {
							return System.currentTimeMillis();
						}
					};
					Long startTime = 0L;
					Long endTime = 0L;
					Object result = null;
					try {
						// 前置拦截
						startTime = (Long) beforeInterceptor.before(proxy, method, args);
						EchoService echoService = new DefaultEchoService();
						result = echoService.echo((String) args[0]); // 目标对象执行
						// 方法执行后置拦截器
						AfterReturnInterceptor afterReturnInterceptor = new AfterReturnInterceptor() {
							@Override
							public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
								return System.currentTimeMillis();
							}
						};
						// 执行 after
						endTime = (Long) afterReturnInterceptor.after(proxy, method, args, result);
					} catch (Exception e) {
						// 异常拦截器（处理方法执行后）
						ExceptionInterceptor interceptor = (proxy1, method1, args1, throwable) -> {
						};
					} finally {
						// finally 后置拦截器
						FinallyInterceptor interceptor = new TimeFinallyInterceptor(startTime, endTime);
						Long costTime = (Long) interceptor.finalize(proxy, method, args, result);
						System.out.println("echo 方法执行的实现：" + costTime + " ms.");
					}

				}
				return null;
			}
		});

		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello,World");
	}
}
