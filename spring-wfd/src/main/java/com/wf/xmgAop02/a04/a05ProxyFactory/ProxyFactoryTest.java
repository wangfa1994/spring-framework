package com.wf.xmgAop02.a04.a05ProxyFactory;


import com.wf.xmgAop02.a04.a04ProxyFactoryBean.DefaultEchoService;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

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
 * Aop代理工厂 一共包括三类 用来产生Aop代理对象，一共会产生两类JdkDy
 * 1.{@link ProxyFactory} 这个代理工厂是独立的一个代理对象，从这里可以直接进行得到代理对象进行操作
 * 2.{@link ProxyFactoryBean} 这个代理工厂是整理到了容器中，可以从spring中容器中进行得到相关属性
 * 3.{@link AspectJProxyFactory} 这个是整合了AspectJ,通过AspectJ进行处理
 *
 * 三种业务场景的代理工厂(ProxyFactory/ProxyFactoryBean/AspectJProxyFactory)。
 * 通过唯一的Aop的代理工厂(AopProxyFactory/DefaultAopProxyFactory)产生两类Aop代理对象(JdkDynamicAopProxy/ObjenesisCglibAopProxy/CglibAopProxy)，
 * 然后通过两类Aop代理对象产生业务代理对象.
 *
 */


public class ProxyFactoryTest {

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

		// MethodInvocation   与 MethodInterceptor
		// AOPProxy  生成代理对象的工厂接口
		// AOP代理对象  和 代理对象   通过Aop代理对象产生代理对象

	}
}
