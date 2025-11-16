package com.wf.xmgAop.a01.jdk00;



import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/*
*  jdk的代理类可以再被代理，但是一般会使用责任链模式，进行一个代理，多个逻辑的处理
*
* 这种方式避免了嵌套代理的开销和复杂性，将所有控制逻辑集中在一处，结构更清晰，性能更好。Spring AOP 等框架采用的就是这种思路
*
* 可以代理：从技术上讲，可以对 JDK 代理类再次进行 JDK 代理，因为它实现了接口。
* 但不推荐：直接嵌套代理会带来调用链复杂、容易出错和性能下降等问题。
* 更好的选择：如果你需要多层处理逻辑，应该在一个 InvocationHandler 中实现责任链模式，而不是创建多层代理对象。这是更成熟、更高效的设计模式
*
* */
public class MoreProxyTest {

	public static void main(String[] args) {
		MethodInterceptor interceptor1 = null;
		MethodInterceptor interceptor2 = null;
		MethodInterceptor interceptor3 = null;

		DefaultEchoService realTarget = new DefaultEchoService();


		// 使用时，只需要创建一层代理
		EchoService proxy = (EchoService) Proxy.newProxyInstance(
				MoreProxyTest.class.getClassLoader(),
				new Class[]{EchoService.class},
				new ChainedInvocationHandler(realTarget, Arrays.asList(interceptor1, interceptor2, interceptor3))
		);
	}

	static class ChainedInvocationHandler implements InvocationHandler {
		private final Object realTarget;
		private final List<MethodInterceptor> interceptors; // 自定义的拦截器接口

		public ChainedInvocationHandler(Object target, List<MethodInterceptor> interceptors) {
			this.realTarget = target;
			this.interceptors = interceptors;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// 创建一个调用链的上下文
			MethodInvocation invocation =null;// new MethodInvocation(realTarget, method, args);

			// 将拦截器组合成责任链，并执行
			for (MethodInterceptor interceptor : interceptors) {
				// 每个拦截器都可以决定是否继续调用链
				//interceptor.before(invocation);
			}

			Object result = invocation.proceed(); // 最终调用真实方法

			for (MethodInterceptor interceptor : interceptors) {
				//result = interceptor.after(result, invocation);
			}
			return result;
		}
	}


}
