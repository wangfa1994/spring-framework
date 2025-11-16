package com.wf.model.customerAop.addProxy;

import com.wf.model.customerAop.addProxy.business.AnotherService;
import com.wf.model.customerAop.addProxy.business.AnotherServiceImpl;
import com.wf.model.customerAop.addProxy.business.GreetingService;
import com.wf.model.customerAop.addProxy.business.GreetingServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Arrays;
import java.util.List;

public class AopDemo {

	public static void main(String[] args) {
		// 1. 创建目标对象
		GreetingService target = new GreetingServiceImpl();

		// 2. 定义拦截器链（AOP 通知）
		List<MethodInterceptor> interceptors = Arrays.asList(
				new SimpleLoggingInterceptor(),
				new SimpleTimingInterceptor()
		);

		// 3. 创建代理对象（动态代理 + AOP 拦截）
		GreetingService proxy = AopProxy.createProxy(target, interceptors, GreetingService.class);

		// 4. 调用代理方法 → 触发 AOP
		proxy.sayHello("Alice");

		AnotherServiceImpl anotherService = new AnotherServiceImpl();
		AnotherService proxy1 = AopProxy.createProxy(anotherService, interceptors, AnotherService.class);
		proxy.sayHello("Another");

	}

}
