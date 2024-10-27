package com.wf.xmgAop.a01.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest {

	public static void main(String[] args) {

		staticProxy();
		System.out.println("======================================");
		dynamicProxy();
	}

	private static void staticProxy() {

		EchoService echoService = new ProxyEchoService(new DefaultEchoService());
		echoService.echo("Hello,World");


	}

	private static void dynamicProxy() {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
					return echoService.echo((String) args[0]);
				}
				return null;
			}
		});

		EchoService echoService = (EchoService) proxy;
		echoService.echo("Hello,World");

		// $Proxy1
		Object proxy2 = Proxy.newProxyInstance(classLoader,
				new Class[]{Comparable.class},
				(proxy1, method, args1) -> {
					return null;
				});

		System.out.println(proxy2);
	}

}
