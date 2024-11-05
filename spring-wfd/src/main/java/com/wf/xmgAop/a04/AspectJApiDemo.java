package com.wf.xmgAop.a04;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 脱离spring容器的AOP的创建
 * AspectJProxyFactory 代理工厂。从代理工厂中得到代理对象，
 *
 * 给我们的代理工厂设置一些通知横切逻辑advice，从代理工厂中得到的对象就可以执行横切逻辑
 */
public class AspectJApiDemo {

	public static void main(String[] args) {

		// 脱离spring容器的处理 AOP的创建
		// 主要使用 AspectJProxyFactory 进行我们的设置与处理

		// 通过创建一个 HashMap 缓存，作为被代理对象
		Map<String, Object> cache = new HashMap<>();
		// 创建 Proxy 工厂(AspectJ)  得到AOP代理对象
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache); // cache 目标对象，被代理的对象
		// 增加 Aspect 配置类 可以从这里解析出来对应的advice。
		//proxyFactory.addAspect(AspectConfiguration.class);
		// 设置暴露代理对象到 AopContext 一定要设置？
		proxyFactory.setExposeProxy(true);
		proxyFactory.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				if ("put".equals(method.getName()) && args.length == 2) {
					Object proxy = AopContext.currentProxy();
					System.out.printf("[MethodBeforeAdvice] 当前存放是 Key: %s , Value : %s ，" +
							"代理对象：%s\n", args[0], args[1], proxy);
				}
			}
		});
		// 通过代理对象存储数据
		Map<String, Object> proxy = proxyFactory.getProxy(); // 这个proxyFactory 创建AOP代理，从AOP代理中得到代理对象，代理工厂获取代理对象
		proxy.put("1", "A");
		System.out.println(proxy.get("1"));
		// AopProxyFactory AOP代理工厂获取AOP的代理对象
	}
}
