package com.wf.xmgAop.a02.customer.interceptor;

import java.lang.reflect.Method;

public interface FinallyInterceptor extends Interceptor {

	Object finalize(Object proxy, Method method, Object[] args, Object returnResult);
}
