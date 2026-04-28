package com.wf.xmgAop03.a12;

//58 AopProxy
// 59 aopProxyFactory
public class a12AopProxyDemo {

	/**
	 * AopProxy
	 *
	 * 字节码提升
	 * class 文件
	 *
	 * JdkDynamicAopProxy 默认的class 类没有被public修饰
	 *
	 * CglibAopProxy 类没有被public修饰
	 *
	 * 配置放在哪里呢？
	 *
	 *
	 * aopProxy 是怎么产生的？
	 *
	 * AopProxyFactory 进行产生的
	 *
	 * DefaultAopProxyFactory 默认实现，产生我们的代理对象的工厂类 只有这一种内建实现，允许被扩展，通过ProxyCreatorSupport
	 *
	 *  为什么会传递advisedSupport  ， 但是是config
	 *
	 *
	 * springAop 支持三种方式代理，动态代理(字节码提升)，cglib字节码提升，以及aspectj适配
	 * 字节码提升
	 *
	 *
	 * * *
	 */
}
