package com.wf.xmgAop.a01.interceptor;

import com.wf.xmgAop.a01.jdk.DefaultEchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 判断模式之后的拦截模式
 *
 * 三种拦截模式
 *
 * 前置通知
 * 后置通知
 * 返回通知
 *
 *
 */


public class AopInterceptorDemo {

	public static void main(String[] args) {
		//normal();
		before();
	}

	// 正常模式
	private static void normal() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxyInstance = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				if (method.getDeclaringClass().isAssignableFrom(EchoService.class)) {
					// 执行我们代理对象的正常逻辑
					EchoService echoService = new EchoServiceImpl();
					Object result = echoService.echo((String) args[0]); // 目标对象执行

					return result;
				}

				return null;
			}
		});

		String echo = ((EchoService) proxyInstance).echo("hello ");
		System.out.println(echo);
	}

	// 前置模式
	private static void before() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxyInstance = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				if (method.getDeclaringClass().isAssignableFrom(EchoService.class)) {
					// 执行前置逻辑
					long start = System.currentTimeMillis();
					Object result; // 目标对象执行
					try {
						// 执行我们代理对象的正常逻辑
						EchoService echoService = new EchoServiceImpl();
						result = echoService.echo((String) args[0]);
					} finally {
						long end = System.currentTimeMillis();
						System.out.println("echo 方法执行的实现：" + (end-start) + " ms."+method.getName());
					}

					return result;
				}

				return null;
			}
		});
		String echo = ((EchoService) proxyInstance).echo("hello");
		System.out.println("最后打印"+echo);
	}

	// 前置模式 这种模式是直接写死的逻辑的，我们是否可以将拦截的逻辑进行抽象呢?



	// 前置模式的 抽象模式   将前置逻辑进行抽象
	static BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
		@Override
		public Object before(Object proxy, Method method, Object[] args) {
			return System.currentTimeMillis();
		}
	};
	private static void beforeAbstract() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxyInstance = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				if (method.getDeclaringClass().isAssignableFrom(EchoService.class)) {
					// 执行前置逻辑
					Long before = (Long)beforeInterceptor.before(proxy, method, args);
					Object result; // 目标对象执行
					try {
						// 执行我们代理对象的正常逻辑
						EchoService echoService = new EchoServiceImpl();
						result = echoService.echo((String) args[0]);
					} finally {
						long end = System.currentTimeMillis();
						System.out.println("echo 方法执行的实现：" + (end-before) + " ms."+method.getName());
					}

					return result;
				}

				return null;
			}
		});
		String echo = ((EchoService) proxyInstance).echo("hello");
		System.out.println("最后打印"+echo);
	}


	/**
	 * 这种带抽象的实现，前置 后置，异常，返回拦截器，这些拦截器是一种高度抽象的接口 这个接口能够帮助你区扩展你的程序
	 * 当你生成一个代理对象的时候，通常可以插拔式的把前置，后置异常等拦截器加到里面去来实现你的功能，这个时候我们只需要
	 * 关注我们的拦截器里面实现的接口，而不需要关心整个上下应用文的实现，那么这个时候给我们的AOP的实现带来灵活度。
	 *
	 * 事实上在spring Aop 或者其他AOp框架里面基本上实现方式也是类同的
	 *
	 *
	 * aop的三种实现 jdk cglib aspectJ
	 * JdkDynamicAopProxy
	 * CglibAopProxy
	 * 都是内部类
	 *
	 *
	 *
	 * AopProxy
	 *
	 *
	 */



}
