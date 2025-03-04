package com.wf.xmgAop02.a09.a01aspjectAfterXml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 36
 * aspectJ 后置动作
 * After Advice 注解
 * 	方法返回后： @AfterReturning
 * 	异常发生后： @AfterThrowing
 * 	Finally执行 @After
 *
 * 	37 xml配置
 */


public class AfterAdviceXmlDemo {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a09/after.xml");

		applicationContext.refresh();

		AfterAdviceXmlDemo proxy = applicationContext.getBean(AfterAdviceXmlDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截

	}


	public void execute() {
		System.out.println("xml execute()...");

	}


}
