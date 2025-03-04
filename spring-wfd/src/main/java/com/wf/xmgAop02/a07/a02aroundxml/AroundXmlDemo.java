package com.wf.xmgAop02.a07.a02aroundxml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 31 xml 配置around Advice
 *
 * xml中，around 和Before的顺序和配置的顺序有关，而在注解中不存在这样的关系
 *
 */

public class AroundXmlDemo {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a07/around.xml");

		applicationContext.refresh();

		AroundXmlDemo proxy = applicationContext.getBean(AroundXmlDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截


	}

	public void execute() {
		System.out.println("xml execute()...");
	}
}
