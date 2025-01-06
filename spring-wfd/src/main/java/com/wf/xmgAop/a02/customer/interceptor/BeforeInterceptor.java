package com.wf.xmgAop.a02.customer.interceptor;

import java.lang.reflect.Method;

public interface BeforeInterceptor extends Interceptor {
	Object before(Object proxy, Method method, Object[] args);
}
