package com.wf.xmgAop.a09;

import com.wf.xmgAop.a07.AroundXmlDemo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AfterAdviceXmlDemo {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop/a09/after.xml");

		applicationContext.refresh();

		AfterAdviceXmlDemo proxy = applicationContext.getBean(AfterAdviceXmlDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截

	}


	public void execute() {
		System.out.println("xml execute()...");

	}


}
