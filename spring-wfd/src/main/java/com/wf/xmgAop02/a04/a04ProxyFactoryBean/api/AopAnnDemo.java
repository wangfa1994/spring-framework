package com.wf.xmgAop02.a04.a04ProxyFactoryBean.api;

import com.wf.xmgAop02.a04.a04ProxyFactoryBean.CustomerMethodInterceptor;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.DefaultEchoService;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.EchoService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 *
 *
 *
 */


public class AopAnnDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopAnnDemo.class);
		EchoService defaultEchoService = context.getBean("proxyFactoryBean",EchoService.class); // 在getObject的时候进行了拦截链的处理
		System.out.println(defaultEchoService.echo("hello"));
		System.out.println(defaultEchoService.sayAop("aop"));
	}



	@Bean
	public DefaultEchoService defaultEchoService(){
		return new DefaultEchoService();
	}

	@Bean
	public CustomerMethodInterceptor customerMethodInterceptor(){
		return new CustomerMethodInterceptor();
	}

	@Bean
	public ProxyFactoryBean proxyFactoryBean(){
		// ProxyFactoryBean 可以说是我们的一个ioc容器中的一个代理对象，可以关联到我们的spring容器中去
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		// 添加我们对应代理对象
		proxyFactoryBean.setTargetName("defaultEchoService");
		// 添加我们的拦截器名称，其实我们的拦截器也就是我们的Advice,这样的话就可以进行了拦截，
		proxyFactoryBean.setInterceptorNames("customerMethodInterceptor");

		// 虽然进行了拦截，但是为什么是进行了所有方法的拦截呢？哪里设置了连接点的相关判断呢？

		return proxyFactoryBean;
	}

}
