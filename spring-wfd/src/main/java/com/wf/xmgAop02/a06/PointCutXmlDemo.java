package com.wf.xmgAop02.a06;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * point cut 的xml表示方式
 */
public class PointCutXmlDemo {

	public static void main(String[] args) {



		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a06/pointCut.xml");

		applicationContext.refresh();

		PointCutXmlDemo proxy = applicationContext.getBean(PointCutXmlDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截


	}

	public void execute() {
		System.out.println("xml execute()...");
	}
}
