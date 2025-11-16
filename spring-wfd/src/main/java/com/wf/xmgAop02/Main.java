package com.wf.xmgAop02;

import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
public class Main {

	/**
	 * @see org.springframework.aop.aspectj.annotation.AspectJProxyFactory (aspectJ)
	 *
	 * @see org.springframework.aop.framework.ProxyFactoryBean (spring)
	 *
	 * @see org.springframework.aop.framework.ProxyFactory (spring)
	 *
	 * @see org.springframework.aop.Pointcut (spring)
	 *
	 * @see org.springframework.aop.support.StaticMethodMatcherPointcut
	 *
	 *
	 * spring aop编程模型有哪些，代表组件有哪些
	 * 注解驱动：@EnableAspectJAutoProxy
	 * XML配置：AOP 与 ioc schema-based 相结合
	 * API编程：jointpoont，pointCut Advice ProxyFactory
	 *
	 * 连接点(JoinPoint): 连接点代表应用程序执行过程中某一个特定的点，特别指可以被AOP拦截的方法调用，所有的方法都可以说是一个连接点。连接点是潜在的拦截点，是程序执行流中一个可以识别的位置。
	 * 切入点(PointCut): 定义了切面中的通知应该在哪些连接点(JoinPoint)上执行。是对连接点的一种筛选机制，通过AspectJ切入点表达式来匹配和定位一系列的连接点
	 * 通知(Advice): 定义切面要执行的操作，真正逻辑处理的地方,代表在切入点匹配到连接点上要执行的代码。包括前置通知/后置通知/异常通知/最终通知/环绕通知
	 * 切面(Aspect)： 封装了横切关注点，一个切面由通知(advice)和切入点(pointCut)构成，切面定义了你要在什么地方(切入点)做什么事情(通知)
	 */
}
