package com.wf.xmgAop03.a05;

import com.wf.xmgAop.a09.AllConfig;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//  AfterReturningAdviceInterceptor
public class AfterReturningAdviceInterceptorDemo {


	public static void main(String[] args) {
		Map<String, Object> cache = new HashMap<>();
		// 创建 Proxy 工厂(AspectJ)
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
		// 增加 Aspect 配置类
		proxyFactory.addAspect(AllConfig.class);
		// 设置暴露代理对象到 AopContext
		proxyFactory.setExposeProxy(true);
		// 添加 AfterReturningAdvice
		proxyFactory.addAdvice(new AfterReturningAdvice() {

			@Override
			public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
					throws Throwable {
				if ("put".equals(method.getName()) && args.length == 2) {
					System.out.printf("[AfterReturningAdvice] 当前存放是 Key: %s , 新存放的 Value : %s , 之前关联的 Value : %s\n ",
							args[0],    // key
							args[1],    // new value
							returnValue // old value
					);
				}
			}
		});

		// 存储数据
		// cache.put("1", "A");
		// 通过代理对象存储数据
		Map<String, Object> proxy = proxyFactory.getProxy();
		proxy.put("1", "A");
		proxy.put("1", "B");
		System.out.println(cache.get("1"));
	}
}
