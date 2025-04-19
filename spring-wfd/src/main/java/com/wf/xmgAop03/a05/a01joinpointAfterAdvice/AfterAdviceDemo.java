package com.wf.xmgAop03.a05.a01joinpointAfterAdvice;


import com.wf.xmgAop02.a09.AllConfig;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AfterAdviceDemo {

	/** 51   joinPoint after advice 标准实现
	 *
	 * @see org.springframework.aop.AfterAdvice
	 * 	 -- {@link org.springframework.aop.AfterReturningAdvice}
	 * 	 -- {@link org.springframework.aop.ThrowsAdvice}
	 *
	 *
	 *
	 * 实现 为什么实现没有直接进行实现上面的接口呢，而是进行了 MethodInterceptor的处理
	 * @see org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor
	 *
	 * @see org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor
	 *
	 *
	 *
	 * 接口 spring提供给开发者的接口
	 * 	-- AfterAdvice
	 * 	-- AfterReturningAdvice
	 * 	-- ThrowsAdvice
	 *
	 *
	 * 	实现  spring内部API层面的，内部使用的  Advice都会包装成Interceptor进行实现
	 * 	-- ThrowsAdviceInterceptor
	 * 	-- AfterReturningAdviceInterceptor
	 *
	 */

	public static void main(String[] args) {
		Map<String, Object> cache = new HashMap<>();
		// 创建 Proxy 工厂(AspectJ)
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);

		// 添加 AfterReturningAdvice    查看 AfterReturningAdviceInterceptor断点进行执行
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
