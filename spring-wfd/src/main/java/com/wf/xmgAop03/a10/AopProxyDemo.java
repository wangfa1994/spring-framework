package com.wf.xmgAop03.a10;

/**
 * 58 AOP代理接口  AopProxy
 *
 *
 * 接口
 * @see org.springframework.aop.framework.AopProxy
 *
 * 实现
 * jdk动态代理
 * @see org.springframework.aop.framework.JdkDynamicAopProxy
 *
 * cglib字节码提升
 * @see org.springframework.aop.framework.CglibAopProxy
 * @see org.springframework.aop.framework.Objenesis CglibAopProxy
 *
 *
 * Aop代理对象
 *
 */
public class AopProxyDemo {

	/**
	 *
	 * AopProxy 代理接口
	 *
	 *
	 * 这个代理接口 存在两种实现
	 *
	 *
	 * aop代理对象是怎么产生的？
	 *
	 * spring的动态代理的整合了三种方式 jdk 动态代理，Cglib动态代理，AspectJ动态代理
	 *  * 但是为什么没有针对AspectJ的代理对象的产生呢？
	 *
	 */
}
