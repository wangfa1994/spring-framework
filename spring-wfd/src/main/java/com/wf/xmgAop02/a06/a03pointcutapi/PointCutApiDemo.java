package com.wf.xmgAop02.a06.a03pointcutapi;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/** 29 api实现pointcut
 *
 * point cut api 实现
 *
 *
 * 核心api PointCut
 * 关联类 ClassFilter   类的过滤器
 * 关联类 MethodMatcher 检查目标方法是否有资格获得通知。方法的匹配器
 *
 * 适配实现 DefaultPointAdvisor 连接到ioc容器中
 *
 *
 * pointcut的 主要目的是过滤，而不是动作
 *
 */
public class PointCutApiDemo {

	public static void main(String[] args) {

		// 首选声明一个point cut 这个是我么自定义的，继承了
		MyPointCutApiPointcut pointcut = new MyPointCutApiPointcut("execute",PointCutApiDemo.class); //这种直接定义了一个pointCut

		// 将 Pointcut 适配成 Advisor  pointcut 无法直接被使用，只是一个过滤判断标准，需要通过Advisor进行转换 这个Advice 也是自定义的
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new MyPointCutApiMethodInterceptor());

		PointCutApiDemo pointCutApiDemo = new PointCutApiDemo();

		ProxyFactory proxyFactory = new ProxyFactory(pointCutApiDemo);

		// Advisor 和advice 关系，实际上 advice本身是一个动作(横切逻辑)，在动作前需要关联一个pointcut ，
		// pointcut 和 advise的关联需要Advisor进行承载
		// advisor 是 pointcut 和 advise的关联
		proxyFactory.addAdvisor(advisor); //  advisor 通知和切点进行了关联的承载


		// 这种方式是指没有指定pointcut的形式，这样的话就会拦截所有的方法进行横切逻辑处理，上面则是通过pointcut进行了关联，就过滤了一些不匹配的方法
		 //proxyFactory.addAdvice(new PointCutApiMethodInterceptor());

		PointCutApiDemo proxy = (PointCutApiDemo)proxyFactory.getProxy();

		proxy.execute();
		proxy.hello();

		// 最主要的还是proxyFactory。设置好目标对象，然后将我们的pointcut与advice进行组合进去，然后就可以生成针对目标对象的拦截哪些方法去执行横切逻辑advice的代理对象

		/**
		 * PointCut 只做过滤，并且pointcut 不会直接被使用，需要通过我们的advisor进行转换，
		 * advise实际上本来就是一个动作，他在动作之前，还需要关联一个的pointcut，进行过滤出对应的joinPoint
		 * 而刚好就使用advisor进行承载两个之间的关系
		 *
		 * 然后我们就可以通过ProxyFactory 产生存在相关逻辑的代理对象了
		 *
		 *
		 * 针对与Pointcut ，顶层接口 Pointcut 只定义了关于类级别的过滤和方法级别的判断
		 * 然后进行了一些抽象实现 比如  StaticMethodMatcherPointcut 等
		 *
		 */

	}


	public void execute() {
		System.out.println("point cut api execute()...");
	}

	public void hello() {
		System.out.println("point cut api hello()...");
	}

}
