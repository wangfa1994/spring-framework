package com.wf.xmgAop03.a07.a02IntroductionAdvisor;

import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;

/**
 *  55.Introduction 与 Advice 连接器  IntroductionAdvisor
 *
 * 接口
 * @see org.springframework.aop.IntroductionAdvisor
 *
 * 元信息
 * @see org.springframework.aop.IntroductionInfo
 *
 * 通用实现
 * @see org.springframework.aop.support.DefaultIntroductionAdvisor
 *
 * aspectJ实现
 * @see org.springframework.aop.aspectj.DeclareParentsAdvisor
 *
 *
 */
public class IntroductionAdvisorDemo implements EchoService,EchoService2 {


	/**
	 *
	 * 当多实现接口的时候，可以通过此进行制定代理那个功能
	 *
	 */

	public static void main(String[] args) {

		IntroductionAdvisorDemo target = new IntroductionAdvisorDemo();
		// ProxyFactory proxyFactory  = new ProxyFactory(target);

		ProxyFactory proxyFactory  = new ProxyFactory();
		proxyFactory.setTarget(target);

		proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("beforeAdvice："+method);
			}
		}, new IntroductionInfo() {
			@Override
			public Class<?>[] getInterfaces() {
				 //return new Class[]{EchoService.class};  // 限制我们使用的接口
				return new Class[]{EchoService.class,EchoService2.class};
			}
		}));


		Object proxy = proxyFactory.getProxy();

		EchoService proxy1 = (EchoService) proxy;
		proxy1.echo("hello");


		EchoService2 proxy2 = (EchoService2) proxy;
		proxy2.echo2("hello2");
	}



	@Override
	public String echo(String message) {
		return null;
	}

	@Override
	public String echo2(String message) {
		return null;
	}

}
