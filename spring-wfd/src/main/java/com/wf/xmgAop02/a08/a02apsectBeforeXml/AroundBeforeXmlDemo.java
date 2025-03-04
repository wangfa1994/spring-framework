package com.wf.xmgAop02.a08.a02apsectBeforeXml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 34 xml 配置BeforeAdvice
 *
 */
public class AroundBeforeXmlDemo {

	// XML的顺序 并不是写死的，会根据配置的顺序进行调增
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a08/beforeAround.xml");

		applicationContext.refresh();

		AroundBeforeXmlDemo proxy = applicationContext.getBean(AroundBeforeXmlDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截


	}

	public void execute() {
		System.out.println("xml execute()...");
	}
}
