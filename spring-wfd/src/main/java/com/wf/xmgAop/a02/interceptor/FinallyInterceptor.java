package com.wf.xmgAop.a02.interceptor;

import java.lang.reflect.Method;

public interface FinallyInterceptor {

	Object finalize(Object proxy, Method method, Object[] args, Object returnResult);
}
