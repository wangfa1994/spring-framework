package com.wf.xmgAop03.a11;

/**
 * 60 jdk  AopProxy 实现   jdk 动态代理对象 实现
 *
 * @see org.springframework.aop.framework.JdkDynamicAopProxy
 *
 * 配置
 * @see org.springframework.aop.framework.AdvisedSupport (配置信息)
 *
 * 来源
 * @see org.springframework.aop.framework.DefaultAopProxyFactory
 *
 *
 * 通过aop代理工厂，产生aop代理对象，从aop代理对象中，得到我们的代理对象
 *
 */
public class JdkDynamicAopProxyDemo {


	public static void main(String[] args) {

		// JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(new AdvisedSupport());

		// 在构造的时候，根据配置进行构造，然后会根据当前配置进行创还能我们的aop代理对象

		// 在得到代理对象的时候，在aop代理对象中，使用了jdk的newProxyInstance方法进行产生代理对象

		// 而且 JdkDynamicAopProxy 还实现了我们的jdk动态代理的 InvocationHandler ，这样的话，在代理的时候，可以通过JdkDynamicAopProxy进行代理逻辑的处理

		// 在执行的时候，使用了invoke方法进行链的处理

		// 即进行了Advice的转换，也进行了代理方法的调用

		// JdkDynamicAopProxy 默认的修饰权限，只能是同包下和子类 可以进行访问

	}
}
