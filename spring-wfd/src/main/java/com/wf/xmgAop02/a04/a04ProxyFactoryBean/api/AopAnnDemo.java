package com.wf.xmgAop02.a04.a04ProxyFactoryBean.api;

import com.wf.xmgAop02.a04.a04ProxyFactoryBean.DefaultEchoService;
import com.wf.xmgAop02.a04.a04ProxyFactoryBean.EchoService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AopAnnDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopAnnDemo.class);
		EchoService defaultEchoService = (EchoService)context.getBean("proxyFactoryBean",EchoService.class);
		System.out.println(defaultEchoService.echo("hello"));
	}

	@Bean
	public DefaultEchoService defaultEchoService(){
		return new DefaultEchoService();
	}

	@Bean
	public ProxyFactoryBean proxyFactoryBean(){
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTargetName("defaultEchoService");
		return proxyFactoryBean;
	}

}
