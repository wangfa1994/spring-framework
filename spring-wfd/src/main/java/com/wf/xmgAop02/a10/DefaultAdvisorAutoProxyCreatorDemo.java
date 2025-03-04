package com.wf.xmgAop02.a10;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 39 自动动态代理
 * 02  DefaultAdvisorAutoProxyCreator 比 比 BeanNameAutoProxyCreator 更智能一些，
 *
 *  会 自动处理我们系统中的 advisor ，这里是advisor ，
 *
 *  adivsor 则又包括我们的methodinterceptor 和 pointcut
 *
 *
 */
public class DefaultAdvisorAutoProxyCreatorDemo {

	public static void main(String[] args) {

		// DefaultAdvisorAutoProxyCreator 能够自动识别我们的advisor 变成全自动 ,不需要我们手动指定拦截器
		// AbstractAdvisorAutoProxyCreator
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();

		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a10/autoProxyDefaultAdvisor.xml");

		applicationContext.refresh();

		EchoService echoService = applicationContext.getBean(EchoService.class);

		System.out.println(echoService.echo("Hello,World"));
	}
}
