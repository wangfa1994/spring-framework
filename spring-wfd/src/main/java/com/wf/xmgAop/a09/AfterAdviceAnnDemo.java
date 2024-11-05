package com.wf.xmgAop.a09;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Configuration
public class AfterAdviceAnnDemo {
	/**
	 * 三类
	 * after
	 * AfterReturning
	 * AfterThrowing
	 *
	 *
	 * 	类似try Catch的操作
	 *
	 *  执行观察顺序
	 *  5.3的
	 *  Around ----> Before ----> execute() ---->   AfterReturning ----> After
	 *  为什么会按照这个顺序执行呢？是在哪里控制的呢？
	 *
	 * 5.2的好像是
	 * Around ----> Before ----> execute() ---->  After  ----> AfterReturning
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(AfterAdviceAnnDemo.class, AllConfig.class);

		applicationContext.refresh();

		AfterAdviceAnnDemo proxy = applicationContext.getBean(AfterAdviceAnnDemo.class);
		proxy.execute();



	}
	public void execute() {
		System.out.println("aroundBefore  execute()...");
	}
}
