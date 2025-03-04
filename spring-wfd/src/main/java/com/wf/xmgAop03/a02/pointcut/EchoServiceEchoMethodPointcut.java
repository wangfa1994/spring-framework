package com.wf.xmgAop03.a02.pointcut;

import com.wf.xmgAop03.a02.EchoService;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;
import java.util.Objects;

// EchoService 的  echo 方法的拦截的 硬编码 静态实现
// 接口实现的
public class EchoServiceEchoMethodPointcut implements Pointcut {

	public static final EchoServiceEchoMethodPointcut INSTANCE = new EchoServiceEchoMethodPointcut();

	@Override
	public ClassFilter getClassFilter() {

		return new ClassFilter() {
			@Override
			public boolean matches(Class<?> clazz) { // clazz 判断的对象 ，，凡是EchoService类和子类都可以通过
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
