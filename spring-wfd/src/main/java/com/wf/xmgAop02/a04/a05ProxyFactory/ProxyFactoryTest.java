package com.wf.xmgAop02.a04.a05ProxyFactory;


import com.wf.xmgAop02.a04.a04ProxyFactoryBean.DefaultEchoService;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.EchoService;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

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
 * Aop代理工厂 一共包括三类 用来产生Aop代理对象，一共会产生两类JdkDy
 * 1.{@link ProxyFactory} 这个代理工厂是独立的一个代理对象，从这里可以直接进行得到代理对象进行操作
 * 2.{@link ProxyFactoryBean} 这个代理工厂是整理到了容器中，可以从spring中容器中进行得到相关属性
 * 3.{@link AspectJProxyFactory} 这个是整合了AspectJ,通过AspectJ进行处理
 *
 * 三种业务场景的代理工厂(ProxyFactory/ProxyFactoryBean/AspectJProxyFactory)。
 * 通过唯一的Aop的代理工厂(AopProxyFactory/DefaultAopProxyFactory)产生两类Aop代理对象(JdkDynamicAopProxy/ObjenesisCglibAopProxy/CglibAopProxy)，
 * 然后通过两类Aop代理对象产生业务代理对象.
 * 中间
 *
 *
 *
 * ProxyFactory 是最初的spring提供的代理类，后来扩展出对应的AspectJProxyFactory 和 ProxyFactoryBean
 * spring最初实现AOP Alliance 规范通过ProxyFactory进行处理AOP，我们进行手动添加我们的advice， 这时候已经存在了一个完整的体系类Pointcut，advisor等
 * 然后后期引入AOP的完整实现框架aspectJ进行aop的扩展处理，可以通过直接使用注解，整合aspectJ的一些功能进行便捷操作AOP
 * spring整合aop alliance 十分粗暴，直接将aop alliance的类进行完全迁移到自己的项目中，在spring-aop模块中的org.aopalliance包下
 *
 *
 * 在 Spring 1.x 时期，只有 代理型 AOP（JDK 动态代理基于接口 + CGLIB 基于类）
 * 到了 Spring 2.x，Spring 想利用 AspectJ 强大的表达能力，于是引入了 AspectJ 注解风格（@Aspect, @Before, @After 等）但默认情况下 底层执行方式还是自己基于代理的 AOP，不是 AspectJ 编译期织入
 * spring3.x -4.x 加强与 AspectJ 的整合（支持 Load-Time Weaving),提供注解配置 + JavaConfig，进一步简化配置。Spring Security / Transaction / Caching 等子项目完全建立在 AOP 基础之上
 * Spring 5.x ~ 6.x 保持 代理型 AOP 为默认模式（简单、轻量),继续兼容 AOP Alliance API,在响应式编程环境（WebFlux）下对代理增强做了优化,向 核心基础设施 渗透：事务、缓存、安全、监控等
 *
 * 1990s AOP 概念提出
 * 2001年 AspectJ 发布，成为完整 AOP 实现
 * 2004年 Spring 1.x 发布，引入代理型 AOP，此时发布了AOP Alliance 提出，定义基础 API（Advice + Interceptor），Spring 整合 AOP Alliance，让 AOP 成为 Spring 容器的基础能力
 * 2006年起。 Spring 2.x 开始整合 AspectJ 注解和切点表达式，企业界主流 AOP 方式逐渐形成：Spring Proxy + AspectJ 表达式
 * 2009年之后，Java EE/CDI 标准开始提供拦截器规范，和 AOP 部分重叠，JBoss AOP、Guice AOP 都围绕 AOP Alliance 发展，但没能超越 Spring AOP 的普及度
 * 2017 ~ 至今 Spring 5.x ~ 6.x 保持代理式 AOP，继续支持 AOP Alliance 和 AspectJ，在响应式编程、云原生环境中依旧稳定可用
 *
 */


public class ProxyFactoryTest {

	public static void main(String[] args) {
		DefaultEchoService defaultEchoService = new DefaultEchoService();


		ProxyFactory proxyFactory  = new ProxyFactory(defaultEchoService); //  一定要设置代理对象 设置目标源



		//proxyFactory.addAdvice(new CustomerMethodInterceptor()); // 添加我们的拦截逻辑， 属于aop 联盟中的method interceptor，继承了Advice

		proxyFactory.addAdvice(new CustomerMethodBeforeInterceptor());


		// 代理对象的产生根据配置类，而proxyFactory就是这个配置类，会从这个配置类中进行解析设置的值 ,然后进行JdkDynamicAopProxy中的Advised即为配置类proxyFactory
		EchoService proxy = (EchoService)proxyFactory.getProxy(); //这里得到是java的动态代理，而不是cglib的动态代理，
		System.out.println("最后主方法打印:"+proxy.echo("hello"));
		System.out.println("最后主方法打印:"+proxy.sayAop("helloAop"));

		// MethodInvocation   与 MethodInterceptor
		// AOPProxy  生成代理对象的工厂接口
		// AOP代理对象  和 代理对象   通过Aop代理对象产生代理对象

		/*
		proxyFactory 好像没有办法进行pointcut的直接过滤，需要自己在通知的逻辑中进行添加判断是否要执行的方法逻辑
		Spring AOP 确实缺乏成熟的 Pointcut 支持，基本只能拦截类/接口中的所有方法，没办法灵活指定。就是简单的jdk动态代理和cglib代理
		逐步演进：1.x 后期加了 Pointcut 接口和一些基本实现（只能做方法名/正则匹配）。
				2.x 开始整合 AspectJ 表达式语法，彻底解决了 Pointcut 的粒度问题，成为今天的主流方式



		* */

	}
}
