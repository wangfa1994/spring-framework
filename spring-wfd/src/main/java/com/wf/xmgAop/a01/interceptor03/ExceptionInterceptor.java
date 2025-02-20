package com.wf.xmgAop.a01.interceptor03;

import java.lang.reflect.Method;

// 异常拦截器
public interface ExceptionInterceptor {

	Object throwInterceptor(Object proxy, Method method, Object[] args, Throwable throwable);
}
