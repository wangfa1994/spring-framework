package com.wf.xmgAop.a08;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AroundBeforeXmlDemo {

	// XML的顺序 并不是写死的，会根据配置的顺序进行调增
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop/a08/beforeAround.xml");

		applicationContext.refresh();

		AroundBeforeXmlDemo proxy = applicationContext.getBean(AroundBeforeXmlDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截


	}

	public void execute() {
		System.out.println("xml execute()...");
	}
}
