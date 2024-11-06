package com.wf.xmgAop03.a05;

import org.springframework.aop.framework.ProxyFactory;

import java.util.Random;

public class ThrowsAdviceInterceptorDemo {

	// ThrowsAdviceInterceptor 的实现，

	public static void main(String[] args) throws Exception {

		ThrowsAdviceInterceptorDemo instance = new ThrowsAdviceInterceptorDemo();

		ProxyFactory proxyFactory = new ProxyFactory(instance);

		proxyFactory.addAdvice(new MyThrowsAdvice());

		ThrowsAdviceInterceptorDemo proxy = (ThrowsAdviceInterceptorDemo) proxyFactory.getProxy();
		proxy.execute();
		proxy.execute();

	}

	public void execute() {
		Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("For Purpose.");
		}
		System.out.println("Executing...");
	}
}
