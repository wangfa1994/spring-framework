package com.wf.model.aop.onecglib.proxyFactoryDemo;

import com.wf.model.aop.onecglib.HelloService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

public class CustomerProxyFactoryDemo {


	public static void main(String[] args) {
		HelloService helloService = new HelloService();

		ProxyFactory proxyFactory = new ProxyFactory();
		// 设置我们的代理对象
		proxyFactory.setTarget(helloService);
		// 设置我们的代理逻辑
		proxyFactory.addAdvice(new MyAdvice());
		// 这样的话，会进行所有的方法拦截进行执行我们的逻辑
		HelloService helloServiceProxy = (HelloService)proxyFactory.getProxy();
		helloServiceProxy.echo("echo");
		helloServiceProxy.sayHello("hello");
		System.out.println("=============================");
		//添加我们的pointcut,切点，只在指定的方法上进行处理我们的代理逻辑,spring会将切点和通知封装到对应的advisor中进行处理
		ProxyFactory pointCutProxyFactory = new ProxyFactory();
		pointCutProxyFactory.setTarget(helloService);
		//MyPointAdvisor myPointAdvisor = new MyPointAdvisor(new MyAdvice(),new MyPointCut());
		DefaultPointcutAdvisor defaultPointcutAdvisor =new DefaultPointcutAdvisor(new MyPointCut(),new MyAdvice());
		pointCutProxyFactory.addAdvisor(defaultPointcutAdvisor);
		HelloService pointCutHelloServiceProxy = (HelloService)pointCutProxyFactory.getProxy();
		pointCutHelloServiceProxy.echo("echo");
		pointCutHelloServiceProxy.sayHello("hello");

	}


	// 自定义实现我们的业务逻辑 使用了前置通知
	static class MyAdvice implements MethodBeforeAdvice {

		@Override
		public void before(Method method, Object[] args, Object target) throws Throwable {
			System.out.println("方法执行之前进行的通知,方法名"+method.getName());
			//Object invoke = method.invoke(target, args);

		}
	}


	// 自定义实现pointcut 然后用于连接点上
	static class MyPointCut implements Pointcut {

		@Override
		public ClassFilter getClassFilter() {
			// 将用于任何类上
			return new ComposablePointcut().getClassFilter();
		}

		@Override
		public MethodMatcher getMethodMatcher() {

			MethodMatcher testJasypt = new MethodMatcher() {
				@Override
				public boolean matches(Method method, Class<?> targetClass) {
					// 过滤到我们的 sayHello 方法上
					if (method.getName().equals("sayHello")) {
						return true;
					}
					return false;
				}

				@Override
				public boolean isRuntime() {
					return false;
				}

				@Override
				public boolean matches(Method method, Class<?> targetClass, Object... args) {
					return false;
				}
			};
			return testJasypt;
		}
	}

	// 自定义实现我们的PointcutAdvisor
	static class MyPointAdvisor implements PointcutAdvisor{

		private Advice advice;
		private Pointcut pointcut;

		public MyPointAdvisor(Advice advice, Pointcut pointcut) {
			this.advice = advice;
			this.pointcut = pointcut;
		}

		@Override
		public Advice getAdvice() {
			return advice;
		}

		@Override
		public boolean isPerInstance() {
			return false;
		}

		@Override
		public Pointcut getPointcut() {
			return pointcut;
		}
	}




}
