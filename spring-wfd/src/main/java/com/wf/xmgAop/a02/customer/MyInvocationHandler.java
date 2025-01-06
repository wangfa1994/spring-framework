package com.wf.xmgAop.a02.customer;

import com.wf.xmgAop.a01.jdk.DefaultEchoService;
import com.wf.xmgAop.a01.jdk.EchoService;
import com.wf.xmgAop.a02.customer.interceptor.Interceptor;
import com.wf.xmgAop.a02.interceptor.AfterReturnInterceptor;
import com.wf.xmgAop.a02.interceptor.BeforeInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyInvocationHandler implements InvocationHandler {

	private List<Interceptor> interceptors; //代理的逻辑链

	private Object target; //代理的对象



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {

			Long startTime = 0L;
			Long endTime = 0L;
			Object result = null;
			try {
				// 前置拦截
				BeforeInterceptor beforeInterceptor = (BeforeInterceptor)interceptors.get(0);

				startTime = (Long) beforeInterceptor.before(proxy, method, args);
				EchoService echoService = new DefaultEchoService();
				result = ((DefaultEchoService)target).echo((String) args[0]); // 目标对象执行

				// 执行 after
				AfterReturnInterceptor afterReturnInterceptor = (AfterReturnInterceptor)interceptors.get(0);
				endTime = (Long) afterReturnInterceptor.after(proxy, method, args, result);
			} catch (Exception e) {
				System.out.println("异常"+e);
			}

		}
		return null;

	}



	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
}
