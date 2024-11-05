package com.wf.xmgAop.a02;

import com.wf.xmgAop.a02.interceptor.FinallyInterceptor;

import java.lang.reflect.Method;

public class TimeFinallyInterceptor implements FinallyInterceptor {

	private final Long startTime;

	private final Long endTime;

	TimeFinallyInterceptor(Long startTime, Long endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
		// 方法执行时间（毫秒）
		Long costTime = endTime - startTime;
		return costTime;
	}
}