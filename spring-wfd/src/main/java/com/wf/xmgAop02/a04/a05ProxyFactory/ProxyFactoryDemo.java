package com.wf.xmgAop02.a04.a05ProxyFactory;


import com.wf.xmgAop02.a04.a04ProxyFactoryBean.DefaultEchoService;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.EchoService;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 26
 * ProxyFactory 代理类的使用 标准代理工厂
 *
 * 脱离spring
 *
 * ProxyFactory 和  ProxyFactoryBean 都是产生代理对象的类，
 *
 * 只不过 ProxyFactory 更加独立，底层
 * ProxyFactoryBean 则更合spring容器进行契合。
 *
 *
 *
 */


public class ProxyFactoryDemo {

	public static void main(String[] args) {
		DefaultEchoService defaultEchoService = new DefaultEchoService();


		ProxyFactory proxyFactory  = new ProxyFactory(defaultEchoService); //  一定要设置代理对象 设置目标源



		proxyFactory.addAdvice(new CustomerMethodInterceptor()); // 添加我们的拦截逻辑， 属于aop 联盟中的method interceptor，继承了Advice


		/*proxyFactory.addAdvice(new AfterReturningAdvice(){
			@Override
			public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
				System.out.println("进入了afterreturning方法中");

			}
		});

		proxyFactory.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("进入了before方法中");
			}
		});*/


		// 代理对象的产生根据配置类，而proxyFactory就是这个配置类，会从这个配置类中进行解析设置的值 ,然后进行JdkDynamicAopProxy中的Advised即为配置类proxyFactory
		EchoService proxy = (EchoService)proxyFactory.getProxy(); //这里得到是java的动态代理，而不是cglib的动态代理，
		System.out.println("最后主方法打印:"+proxy.echo("hello"));

		//MethodInvocation   与 MethodInterceptor 
		// AOPProxy  生成代理对象的工厂接口
		// AOP代理对象  和 代理对象   通过Aop代理对象产生代理对象
	}
}
