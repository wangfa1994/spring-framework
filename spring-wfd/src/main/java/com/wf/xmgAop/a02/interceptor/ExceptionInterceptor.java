package com.wf.xmgAop.a02.interceptor;

import java.lang.reflect.Method;

public interface ExceptionInterceptor {
	void intercept(Object proxy, Method method, Object[] args, Throwable throwable);
}
