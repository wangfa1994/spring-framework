package com.wf.xmgAop.a01.cglib;

import com.wf.xmgAop.a01.jdk00.DefaultEchoService;
import com.wf.xmgAop.a01.jdk00.EchoService;
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
	 * 切面 aspect 整合了切点和通知的类
	 *
	 *
	 * 字节码提升的四种类型 (jdk代理，Cglib代理 javassist代理 ，aspectJ代理)
	 *
	 * 动态代理包括jdk动态代理
	 * 基于接口代理和cglib库代理基于类代理(字节码提升)，
	 * aspectJ框架的使用需要aspectJ编译器(ajc)和运行类库
	 * asm字节码提升
	 * 还有javassist库提升字节码
	 * mybatis使用的是jdk，cglib javassist 三种方式进行字节码提升
	 *
	 *
	 * jdk动态代理：通过java.lang.reflect.Proxy类为一个或多个接口动态生成代理类。代理类实现了和目标对象相同的接口，并且在调用方法前后增加额外的操作，
	 * 简单易用，不需要额外的库进行支持。但是只能代理实现了接口的类。动态织入
	 *
	 * cglib代理：通过cglib库动态生成目标类的子类，从而实现对目标类的方法进行拦截和增强。cglib使用asm字节码生成框架直接操作字节码。
	 * 可以直接代理没有实现接口的类，但是性能略低于jdk动态代理，并且生成的代理类比jdk动态代理复杂。动态织入
	 *
	 * javassist代理：直接进行自己的字节码操作，而不依赖于asm
	 *
	 * AspectJ 既包括动态织入又包括静态织入
	 * 		静态织入：在编译阶段将切面代码织入到目标类中,生成新的字节码文件
	 * 		动态织入：在运行时通过字节码操作技术将切面代码植入到目标类中，
	 * 提供了丰富的aop功能，可以灵活的在不停节点进行织入，但是配置相对复杂，学习曲线高
	 *
	 * spring也进行了cglib的适配,利用cglib进行动态代理
	 *
	 * aspectJ适配实现 又是什么？
	 * aspectJ里面会有一些aspect，join points，pointcuts等注解，aspectj编译器会进行这些注解的处理，生成新的类的字节码
	 *
	 *
	 * aspectJ原生语法太过复杂，所以spring针对aspectJ进行了包装，只使用了aspectJ的注解相关，然后代理是基于Cglib进行处理的
	 *
	 *
	 * 共同目标：三者都旨在解决横切关注点（cross-cutting concerns）的问题，比如日志记录、事务管理等，这些关注点通常会跨越应用程序中的多个模块。
	 * 实现方式：JDK动态代理和CGLIB都是基于代理的设计模式来实现AOP，而AspectJ则提供了更高级别的抽象，允许开发者直接操作程序结构，而不必关心底层是如何实现的。
	 * 适用场景：如果目标对象实现了接口，优先考虑使用JDK动态代理；如果需要代理没有接口的类，则可以选择CGLIB；当涉及到复杂的AOP需求时，AspectJ可能是更好的选择，因为它提供了更强大的功能和灵活性。
	 *
	 *
	 *
	 *
	 * spring aop 纯java实现，运行时进行处理，不是在编译时进行处理 ，仅支持方法级别的aop，不是完整的aop实现
	 *
	 *
	 *
	 */







	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		// 指定super Class
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
				System.out.println("方法执行之前可以进行逻辑处理");
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


	/**
	 * 在jdk动态代理中，我们需要传递目标类实例，然后再代理逻辑中通过传递进去的目标类实例进行手动执行方法。
	 * 但是在Cglib中，我们只进行了 目标类设定，并没有传递目标类的实例对象
	 *
	 */


}
