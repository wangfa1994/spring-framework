package com.wf.xmgAop03.a12;

/**
 * 63 Advisor 链工厂接口与实现
 *
 * 核心api
 * @see org.springframework.aop.framework.AdvisorChainFactory
 * 顾问链的工厂接口。
 *
 *
 * 默认实现
 * @see org.springframework.aop.framework.DefaultAdvisorChainFactory
 *
 * 特殊实现
 * @see org.springframework.aop.framework.InterceptorAndDynamicMethodMatcher
 *
 */
public class A02AdvisorChainFactory {

	/**
	 *
	 * 把Advice 转换成 MethodInterceptor 进行使用
	 *
	 * 里面只有一个方法，通过遍历配置类中的advisor，进行去查找适配当前方法的advisor，然后将对应的advisor 转换成对应的  MethodInterceptor
	 *
	 *
	 *
	 *
	 *
	 */
}
