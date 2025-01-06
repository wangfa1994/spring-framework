package com.wf.xmgAop.a02.customer.interceptor;

import java.lang.reflect.Method;

public interface ExceptionInterceptor extends Interceptor {
	void intercept(Object proxy, Method method, Object[] args, Throwable throwable);
}
