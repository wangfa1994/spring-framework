package com.wf.xmgAop.a07;

import com.wf.xmgAop.a06.PointCutXmlDemo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AroundXmlDemo {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop/a07/around.xml");

		applicationContext.refresh();

		AroundXmlDemo proxy = applicationContext.getBean(AroundXmlDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截


	}

	public void execute() {
		System.out.println("xml execute()...");
	}
}
