package com.wf.xmgAop.a06;

import com.wf.xmgAop.a05.EchoServiceMethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * point cut api 实现
 *
 * PointCut
 * ClassFilter   类的过滤器
 * MethodMatcher 检查目标方法是否有资格获得通知
 *
 * 适配实现 DefaultPointAdvisor 连接到ioc容器中
 *
 */
public class PointCutApiDemo {

	public static void main(String[] args) {

		// 首选声明一个point cut
		PointCutApiPointcut pointcut = new PointCutApiPointcut("execute",PointCutApiDemo.class); //这种直接定义了一个pointCut

		// 将 Pointcut 适配成 Advisor  pointcut 无法直接被使用，只是一个过滤判断标准，需要通过Advisor进行转换
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new PointCutApiMethodInterceptor());

		PointCutApiDemo pointCutApiDemo = new PointCutApiDemo();

		ProxyFactory proxyFactory = new ProxyFactory(pointCutApiDemo);

		// Advisor 和advise 关系，实际上 advise本身是一个动作(横切逻辑)，在动作前需要关联一个pointcut ，
		// pointcut 和 advise的关联需要Advisor进行承载
		// advisor 是 pointcut 和 advise的关联
		proxyFactory.addAdvisor(advisor); //  advisor 通知和切点进行了关联的承载


		// 这种方式是指没有指定pointcut的形式，这样的话就会拦截所有的方法进行横切逻辑处理，上面则是通过pointcut进行了关联，就过滤了一些不匹配的方法
		 //proxyFactory.addAdvice(new PointCutApiMethodInterceptor());

		PointCutApiDemo proxy = (PointCutApiDemo)proxyFactory.getProxy();

		proxy.execute();
		proxy.hello();

		// 最主要的还是proxyFactory。设置好目标对象，然后将我们的pointcut与advice进行组合进去，然后就可以生成针对目标对象的拦截哪些方法去执行横切逻辑advice的代理对象

	}


	public void execute() {
		System.out.println("point cut api execute()...");
	}

	public void hello() {
		System.out.println("point cut api hello()...");
	}

}
