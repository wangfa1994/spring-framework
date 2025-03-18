package com.wf.xmgAop03.a07;

/**
 * 54 Pointcut 与 Advice 链接器  PointcutAdvisor
 * Pointcut  与 advisor 关联组合的对象
 *
 *
 * 接口
 * @see org.springframework.aop.PointcutAdvisor
 *
 * 通用实现
 * @see org.springframework.aop.support.DefaultPointcutAdvisor
 *
 * AspectJ实现
 * @see org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor
 * @see org.springframework.aop.aspectj.AspectJPointcutAdvisor
 *
 * 静态方法实现
 * @see org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor
 *
 * IOC容器实现
 * @see org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor
 */
public class A01PointcutAdvisorDemo {

	/**
	 *
	 * PointcutAdvisor 存在pointcut 和Advice功能
	 *
	 *  通用实现
	 * DefaultPointcutAdvisor  一个包含两个功能的容器 并且具备顺序
	 *
	 * AspectJ实现
	 * 	AspectJExpressionPointcutAdvisor
	 * 	AspectJPointcutAdvisor
	 *
	 * 	静态方法实现
	 * 	StaticMethodMatcherPointcutAdvisor
	 *
	 * 	IOC容器实现
	 * 	AbstractBeanFactoryPointcutAdvisor
	 *
	 *
	 */
}
