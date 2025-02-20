package com.wf.xmgAop.a01.interceptor03;

import java.lang.reflect.Method;

public interface FinallyInterceptor {


	Object finalize(Object proxy, Method method, Object[] args, Object returnResult);

}
