package com.wf.xmgAop.a01.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest {

	public static void main(String[] args) {
		// jdk 静态代理存在 继承或者组合进行实现
		//staticProxy();
		System.out.println("======================================");
		dynamicProxy();
	}

	private static void staticProxy() {

		// 组合的方式
		EchoService echoService = new ProxyEchoService(new DefaultEchoService());
		echoService.echo("Hello,World");

		// 继承的方式
		ProxyExtendEchoService proxyExtendEchoService = new ProxyExtendEchoService();
		proxyExtendEchoService.echo("Hello,World");


	}

	private static void dynamicProxy() {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// classLoader 新生成的字节码的类使用的是那个类加载器，要属于当前classLoad的

		// $Proxy0
		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 代理类的代理可以存在多个类，是一个类数组的形式Class[]的形式。不同的可以有不同的逻辑
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
					return echoService.echo((String) args[0]);
				}
				return null;
			}
		});

		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello,World");

		// $Proxy1 观察返回的对象是累增的
		Object proxy2 = Proxy.newProxyInstance(classLoader,
				new Class[]{Comparable.class},
				(proxy1, method, args1) -> {
					return null;
				});

		System.out.println(proxy2);
	}

}
