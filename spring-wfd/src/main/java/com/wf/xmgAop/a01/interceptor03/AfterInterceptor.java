package com.wf.xmgAop.a01.interceptor03;

import java.lang.reflect.Method;

// 前置拦截器
public interface AfterInterceptor {


	// 后置拦截器   参数的定义 可以  根据代理的方法添加对应的参数
	// returnResult 执行方法返回的结果
	Object after(Object proxy, Method method, Object[] args,Object returnResult);
}
