package com.wf.xmgAop03.a09;

/**
 * 57 AdvisorAdapter 实现
 *
 *
 * 接口
 * @see org.springframework.aop.framework.adapter.AdvisorAdapter
 *
 *  @see org.springframework.aop.MethodBeforeAdvice 实现
 *   @see org.springframework.aop.framework.adapter.MethodBeforeAdviceAdapter // 这个是默认访问权限
 *
 *   @see org.springframework.aop.AfterReturningAdvice 实现
 *   @see org.springframework.aop.framework.adapter.AfterReturningAdviceAdapter //
 *
 *   @see org.springframework.aop.ThrowsAdvice 实现
 *   @see org.springframework.aop.framework.adapter.ThrowsAdviceAdapter //
 *
 *
 *
 * @see  org.springframework.aop.framework.adapter.AdvisorAdapterRegistry
 *
 *
 */
public class A02AdvisorAdapterDemo {
	/**
	 *  AdvisorAdapter 是把Advice转换成 MethodInterceptor
	 *
	 *  不同的advice 有不同的adapter适配器进行适配转换，但是每次都需要我们进行逻辑判断，然后进行转换，
	 *  于是Spring 帮我们内置了一个适配注册器，我们可以通过这个注册器可以轻松的进行转换 AdvisorAdapterRegistry
	 *
	 *
	 *
	 *  AdvisorAdapterRegistry 是把Advisor 适配成   MethodInterceptor
	 *
	 */
}
