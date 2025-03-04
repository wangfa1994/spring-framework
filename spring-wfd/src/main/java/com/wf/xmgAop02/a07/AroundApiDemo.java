package com.wf.xmgAop02.a07;

// 32 为什么spring aop 没有设计around advice

/**
 * aspectJ 中的@around 需要与  org.aspectj.lang.ProceedingJoinPoint 配合执行被代理的方法
 *
 * ProceedingJoinPoint#proceed() 方法类似于 Java Method#invoke(Object obj, Object... args)
 *
 * spring aop 底层API ProxyFactory 可通过addAdvice 方法 与 Advice实现关联
 *
 * 接口 Advice 是Interceptor的父亲接口，而接口MethodInterceptor 又扩展了 Interceptor
 *
 * MethodInterceptor的 invoke 方法参数MethodInvocation 与 ProceedingJoinPoint 类似
 *
 *
 * Advice
 * 	有BeforeAdvice实现 没有AroundAdvice实现
 * 	但是 MethodInterceptor 实现了Interceptor 而 Interceptor 则又实现了 Advice
 * 	在 MethodInterceptor 接口中则存在  Object invoke(@Nonnull MethodInvocation invocation) throws Throwable
 * 	这个方法的参数 MethodInvocation 竟然是 JoinPoint的实现，于是存在了JoinPoint的proceed方法
 *
 *
 * 	于是我们不需要AroundAdvice ，可以直接使用MethodInterceptor进行处理
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class AroundApiDemo {

}
