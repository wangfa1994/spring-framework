package com.wf.xmgAop.a02.customer.interceptor;

import java.lang.reflect.Method;

public interface AfterReturnInterceptor extends Interceptor {
	Object after(Object proxy, Method method, Object[] args, Object returnResult);
}
