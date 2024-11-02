package com.wf.xmgAop.a01.cglib;

import com.wf.xmgAop.a01.jdk.DefaultEchoService;
import com.wf.xmgAop.a01.jdk.EchoService;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyTest {

	/**
	 *
	 * 连接点 join point  所有的方法都是连接点
	 * 切点 point cut  从连接点中通过切点表达式进行过滤出来的连接点，叫做切点
	 * 通知 advice  横切逻辑，需要将我们的横切逻辑应用在我们通过切点表达式过滤出来的连接点。
	 * 切面 aspect
	 *
	 *
	 * 字节码提升的三种类型
	 *
	 * 动态代理包括jdk动态代理 基于接口代理和cglib代理基于类代理(字节码提升)，aspectJ的使用
	 *
	 * aspectJ适配实现 又是什么？
	 * aspectJ里面会有一些aspect，join points，pointcuts等注解，aspectj编译器会进行这些注解的处理，生成新的类的字节码
	 *
	 *
	 * aspectJ原生语法太过复杂，所以spring针对aspectJ进行了包装
	 *
	 *
	 * 共同目标：三者都旨在解决横切关注点（cross-cutting concerns）的问题，比如日志记录、事务管理等，这些关注点通常会跨越应用程序中的多个模块。
	 * 实现方式：JDK动态代理和CGLIB都是基于代理的设计模式来实现AOP，而AspectJ则提供了更高级别的抽象，允许开发者直接操作程序结构，而不必关心底层是如何实现的。
	 * 适用场景：如果目标对象实现了接口，优先考虑使用JDK动态代理；如果需要代理没有接口的类，则可以选择CGLIB；当涉及到复杂的AOP需求时，AspectJ可能是更好的选择，因为它提供了更强大的功能和灵活性。
	 *
	 *
	 *
	 */







	public static void main(String[] args) {

		Enhancer enhancer = new Enhancer();
		// 指定拦截类
		enhancer.setSuperclass(DefaultEchoService.class);

		// 拦截指定接口
		enhancer.setInterfaces(new Class[]{EchoService.class});

		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				long startTime = System.currentTimeMillis();
				// Source -> CGLIB 子类
				// 目标类  -> DefaultEchoService
				// 错误使用
//                Object result = method.invoke(source, args);
				// 正确的方法调用
				Object result = methodProxy.invokeSuper(source, args);
				long costTime = System.currentTimeMillis() - startTime;
				System.out.println("[CGLIB 字节码提升] echo 方法执行的实现：" + costTime + " ms.");
				return result;
			}
		});
		// 创建代理对象
		EchoService echoService = (EchoService) enhancer.create();
		// 输出执行结果
		System.out.println(echoService.echo("Hello,World"));
	}

}
