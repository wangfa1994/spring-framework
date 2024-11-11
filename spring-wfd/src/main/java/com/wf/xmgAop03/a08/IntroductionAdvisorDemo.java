package com.wf.xmgAop03.a08;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.aopalliance.aop.Advice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;
import java.util.Map;

public class IntroductionAdvisorDemo {

	/**
	 * j接口
	 * 	IntroductionAdvisor
	 *
	 * 	元信息
	 * 	introductionInfo
	 *
	 * 	通用实现
	 * 	DefaultIntroductionAdvisor
	 *
	 * 	aspectJ实现
	 * 	DeclareParentsAdvisor
	 *
	 *  实现了多个接口，可以通过IntroductionAdvisor 只进行其中的某一个
	 *
	 *  接口的一个约束
	 */



	public static void main(String[] args) {


		DefaultEchoService target = new DefaultEchoService();

		ProxyFactory proxyFactory = new ProxyFactory(target);

		//ProxyFactory proxyFactory = new ProxyFactory(); proxyFactory.setTarget(target);

		// 添加 IntroductionInfo
		proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("BeforeAdvice:" + method.getName());
			}
		}, new IntroductionInfo() {
			@Override
			public Class<?>[] getInterfaces() {
				return new Class[]{AnotherEchoService.class,EchoService.class, Map.class}; // 需要被目标对象实现，如果超过了，转换不会出错，但是使用会进行出错
			}
		}
		));

		Object proxy = proxyFactory.getProxy();

		AnotherEchoService proxy1 = (AnotherEchoService) proxy;
		proxy1.anotherEcho("AnotherEchoService");


		EchoService proxy2 = (EchoService) proxy;
		proxy2.echo("EchoService");

		Map proxy3 = (Map) proxy; // 可以转换，但是不能使用，因为没有实现对应的方法
		proxy3.put("1","A");

	}
}
