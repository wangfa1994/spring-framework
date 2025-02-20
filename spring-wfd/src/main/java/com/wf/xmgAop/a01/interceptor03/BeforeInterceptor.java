package com.wf.xmgAop.a01.interceptor03;

import java.lang.reflect.Method;

// 前置拦截器
public interface BeforeInterceptor {


	// 前置拦截器   参数的定义 可以  根据代理的方法添加对应的参数
	Object before(Object proxy, Method method, Object[] args);
}
