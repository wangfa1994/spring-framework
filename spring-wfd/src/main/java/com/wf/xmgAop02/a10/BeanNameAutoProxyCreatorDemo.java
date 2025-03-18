package com.wf.xmgAop02.a10;


import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** AspectJ 的另外使用方法    AOP的自动动态代理
 * 39 自动动态代理
 *
 * 代表实现 BeanNameAutoProxyCreator  @EnableAspectJAutoProxy的底层实现
 * DefaultAdvisorAutoProxyCreator
 * AnnotationAwareAspectJAutoProxyCreator
 *
 */
// 自动代理

public class BeanNameAutoProxyCreatorDemo {

	public static void main(String[] args) {
		// BeanNameAutoProxyCreator  最终属于 SmartInstantiationAwareBeanPostProcessor 属于  beanPostProcessor

		ann();
		System.out.println("=============");
		//xml();

	}

	private static void ann() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BeanNameAutoProxyCreatorDemo.class);
		context.refresh();
		EchoService echoService = context.getBean(EchoService.class);
		System.out.println(echoService.echo("Hello,World"));

		AnotherService anotherService = context.getBean(AnotherService.class);
		System.out.println(anotherService.another("Hello,World,another"));
	}

	@Bean
	public EchoService echoService(){
		return new DefaultEchoService();
	}
	@Bean
	public AnotherService anotherService(){
		return new AnotherService();
	}
	@Bean
	public EchoServiceMethodInterceptor echoServiceMethodInterceptor(){
		return new EchoServiceMethodInterceptor();
	}

	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){

		//BeanNameAutoProxyCreator ，继承了 AbstractAutoProxyCreator 而这个AbstractAutoProxyCreator则实现了SmartInstantiationAwareBeanPostProcessor
		// 所以会在进行创建对象的时候进行beanPostprocess的拦截，然后会进行是否需要代理的判断， 这个主要是根据名称进行判断，如果需要的话则会进行代理对象产生
		BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		creator.setInterceptorNames("echoServiceMethodInterceptor"); // 设置InterceptorNames
		creator.setBeanNames("echo*");
		return creator;
	}




	private static void xml() {
		// 在xml中进行了 BeanNameAutoProxyCreator的配置，然后进行相关字段拦截 这里可以设置beanNames通配符，这个可以进行稍微智能的自动代理，比ProxyFactoryBean的主动指定自动一些
		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a10/autoProxyBeanName.xml");

		applicationContext.refresh();

		EchoService echoService = applicationContext.getBean(EchoService.class); // 这里可以直接得到我们的代理对象，只是经过配置

		System.out.println(echoService.echo("Hello,World"));
	}
}
