package com.wf.xmgAop03.a02;

// PointCut  体系 类
//
import com.wf.xmgAop03.a02.methodinterceptor.EchoServiceMethodInterceptor;
import com.wf.xmgAop03.a02.pointcut.EchoServiceEchoMethodPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/** 44 . joinpoint条件接口
 *
 *   joinpoint 条件接口 pointcut
 * @see org.springframework.aop.Pointcut
 *
 *   核心组件
 *   	- 类过滤器 classFilter
 *   	- 方法匹配器 MethodMatcher
 *
 *
 */
public class A01PointCutDemo {

	/**  过滤模式
	 *  ClassFilter
	 *  判断模式
	 * MethodMatcher

	 */

	public static void main(String[] args) {


		EchoServiceEchoMethodPointcut pointcut = new EchoServiceEchoMethodPointcut();

		// 将 Pointcut 适配成 Advisor  pointcut 无法直接被使用，只是一个过滤判断标准，需要通过Advisor进行转换
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());

		DefaultEchoService defaultEchoService = new DefaultEchoService();

		ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

		// Advisor 和advise 关系，实际上 advise本身是一个动作，在动作前需要关联一个pointcut ，
		// pointcut 和 advise的关联需要Advisor进行承载
		// advisor 是 pointcut 和 advise的关联
		proxyFactory.addAdvisor(advisor);

		EchoService proxy = (EchoService)proxyFactory.getProxy();

		System.out.println(proxy.echo("hello"));

	}


}
