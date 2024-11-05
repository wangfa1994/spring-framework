package com.wf.xmgAop03.a02;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;
import java.util.Objects;

public class EchoServicePointcut implements Pointcut {

	public static final EchoServicePointcut INSTANCE = new EchoServicePointcut();

	@Override
	public ClassFilter getClassFilter() {

		return new ClassFilter() {
			@Override
			public boolean matches(Class<?> clazz) {
				return EchoService.class.isAssignableFrom(clazz);
			}
		};
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return new MethodMatcher() {
			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				return "echo".equals(method.getName()) &&
						method.getParameterTypes().length == 1 &&
						Objects.equals(String.class,method.getParameterTypes()[0])
						;
			}

			@Override
			public boolean isRuntime() {
				return false;
			}

			@Override
			public boolean matches(Method method, Class<?> targetClass, Object... args) {
				return false;
			}
		};
	}
}
