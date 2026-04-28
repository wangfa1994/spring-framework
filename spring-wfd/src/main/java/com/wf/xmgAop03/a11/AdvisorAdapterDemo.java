package com.wf.xmgAop03.a11;

// 57课   AdvisorAdapter的实现
public class AdvisorAdapterDemo {

	/**
	 *  AdvisorAdapter 三种实现
	 *  AdvisorAdapter 是公开的
	 *  所以我们可以实现 AdvisorAdapter 进行扩展
	 *
	 *  命名规范 但是三个实现类并且是内部的类
	 *
	 *  MethodBeforeAdviceAdapter
	 *
	 *  ThrowsAdviceAdapter
	 *
	 *  AfterReturningAdviceAdapter
	 *
	 * AdvisorAdapter 的业务逻辑就是是否支持advice 并且会将advisor 转换成 MethodInterceptor
	 *
	 *
	 * AdvisorAdapterRegistry 这个是他的注册器？
	 *
	 * 一个joinPoint 可以被多个pointcut适配
	 * 一个pointcut 可以存在多个advice
	 *
	 *
	 *
	 */

}
