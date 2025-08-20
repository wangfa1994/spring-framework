package com.wf.xmgAop03.a09;

/**
 * 56 advisor 的 interceptor 适配器
 *
 * 接口
 * @see org.springframework.aop.framework.adapter.AdvisorAdapter
 *
 * @see org.springframework.aop.MethodBeforeAdvice 实现
 * @see org.springframework.aop.framework.adapter.MethodBeforeAdviceAdapter // 这个是默认访问权限
 *
 * @see org.springframework.aop.AfterReturningAdvice 实现
 * @see org.springframework.aop.framework.adapter.AfterReturningAdviceAdapter //
 *
 * @see org.springframework.aop.ThrowsAdvice 实现
 * @see org.springframework.aop.framework.adapter.ThrowsAdviceAdapter //
 *
 *
 */
public class A01AdvisorInterceptorDemo {

	/**
	 * AdvisorAdapter 接口 两个方法， 一个是判断是否支持的Advice ，如果是支持的，则会进行转换成对应的methodInterceptor
	 *
	 *
	 *
	 *
	 */
}
