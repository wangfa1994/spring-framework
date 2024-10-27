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
