package com.wf.xmgAop02.a10;


import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** AspectJ 的另外使用方法    AOP的自动动态代理
 * 39 自动动态代理
 *
 * 代表实现 BeanNameAtoProxyCreator  @EnableAspectJAutoProxy的底层实现
 * DefaultAdvisorAutoProxyCreator
 * AnnotationAwareAspectJAutoProxyCreator
 *
 */
// 自动代理

public class BeanNameAutoProxyCreatorDemo {

	public static void main(String[] args) {
		// BeanNameAutoProxyCreator  最终属于 SmartInstantiationAwareBeanPostProcessor 属于  beanPostProcessor

		/*BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		creator.setBeanNames("echo*,beanName");
		EchoServiceMethodInterceptor echoServiceMethodInterceptor = new EchoServiceMethodInterceptor();
		creator.setInterceptorNames("echoServiceMethodInterceptor");*/

		// 在xml中进行了 BeanNameAutoProxyCreator的配置，然后进行相关字段拦截 这里可以设置beanNames通配符，这个可以进行稍微智能的自动代理，比ProxyFactoryBean的主动指定自动一些
		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a10/autoProxyBeanName.xml");

		applicationContext.refresh();

		EchoService echoService = applicationContext.getBean(EchoService.class); // 这里可以直接得到我们的代理对象，只是经过配置

		System.out.println(echoService.echo("Hello,World"));




	}
}
