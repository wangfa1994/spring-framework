package com.wf.xmgAop.a02.interceptor;

import java.lang.reflect.Method;

public interface BeforeInterceptor {
	Object before(Object proxy, Method method, Object[] args);
}
