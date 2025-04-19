package com.wf.xmgAop03.a11;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 61 cglibAopProxy实现
 *
 * @see org.springframework.aop.framework.CglibAopProxy
 *
 *
 * @see org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept(Object, Method, Object[], MethodProxy)
 */
public class CglibAopProxyDemo {

	/**
	 *  CglibAopProxy 代理工厂，这个是整合了cglib字节码生成类库，并且Spring进行了重新打包cglib库。
	 *
	 *  在这个类中。进行了cglib的MethodInterceptor的实现 DynamicAdvisedInterceptor [org.springframework.cglib.proxy.MethodInterceptor]
	 *
	 *  然后再cglib动态类进行执行的时候，会进行回调到这个方法中
	 *
	 *
	 *  在cglib代理的时候会进入这个DynamicAdvisedInterceptor中的intercept方法，在这里进行了Advice的通知处理
	 *
	 *
	 * 而关于Advice通知的调用，则会被封装成AOP中的 MethodInterceptor [org.aopalliance.intercept.MethodInterceptor]
	 * 然后通过AOP的 org.aopalliance.intercept.MethodInvocation 进行调用Advice相关逻辑
	 *
	 *
	 *
	 */
}
