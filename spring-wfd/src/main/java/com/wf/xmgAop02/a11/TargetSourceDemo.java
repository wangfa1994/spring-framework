package com.wf.xmgAop02.a11;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 40 替换 targetSource
 *
 * @see org.springframework.aop.TargetSource
 *
 * 实现类
 * SingletonTargetSource
 * EmptyTargetSource
 * HotSwappableTargetSource
 *
 *
 * targetSource 主要参与  ProxyFactoryBean的处理，会被ProxyFactoryBean进行目标对象包装
 * 这里关联到了 spring IOC 容器中了
 */
public class TargetSourceDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(TargetSourceDemo.class);
		annotationConfigApplicationContext.refresh();

		EchoService proxyFactoryBean = annotationConfigApplicationContext.getBean("proxyFactoryBean", EchoService.class);


		System.out.println(proxyFactoryBean.echo("hello"));
		//xmlTargetSource();
	}

	@Bean
	public ProxyFactoryBean proxyFactoryBean(EchoService echoService){
		// 很神奇的操作就是，这个ProxyFactoryBean 是一个工厂bean，获取这个对象的时候会调用对应的getObject方法，这个方法中就进行了Advisor链的创建，然后pointcut就是在这里进行默认拦截所有的处理
		// DefaultPointcutAdvisor 默认为true
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		//proxyFactoryBean.setTarget(echoService);
		proxyFactoryBean.setTargetSource(new SingletonTargetSource(echoService));
		// 虽然说他是Advice，但是是什么前置，后置，还是什么Advice？
		proxyFactoryBean.setInterceptorNames("echoServiceMethodInterceptor"); //这个其实就是Advice，我们的增强行为 ,过滤拦截是在哪里处理的呢？Pointcut
		return proxyFactoryBean;
	}
	@Bean
	public EchoService echoService(){
		return new DefaultEchoService();
	}
	@Bean
	public EchoServiceMethodInterceptor echoServiceMethodInterceptor(){
		return new EchoServiceMethodInterceptor();
	}




	//================================xml
	private static void xmlTargetSource() {
		//TargetSource targetSource  = new SingletonTargetSource();
		// ProxyFactoryBean 中存在target source 用于追踪目标源

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a11/targetSource.xml");

		EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

		System.out.println(echoService.echo("Hello,World"));

		context.close();
	}


}
