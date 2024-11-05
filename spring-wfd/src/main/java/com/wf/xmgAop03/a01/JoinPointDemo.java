package com.wf.xmgAop03.a01;


// // aspect  没有进行高级抽象 Joinpoint  pointcut 则进行了高级抽象，有对应的类进行处理
// 接入点接口 joinpoint
// joinpoint 设计 看类的设计
public class JoinPointDemo {

	/**
	 * Interceptor执行上下文  Invocation
	 * 方法拦截器执行上下文 methodInvocation   ReflectiveMethodInvocation   CglibMethodInvocation
	 *
	 *
	 * 	 * JoinPoint
	 * 	 *    --Invocation
	 * 	 *    	-- MethodInvocation （构造器的不支持）
	 * 	 *    		-- ProxyMethodInvocation
	 * 	 *    			-- ReflectiveMethodInvocation
	 * 	 *    				-- CglibMethodInvocation
	 *
	 */




}
