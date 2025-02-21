package com.wf.xmgAop02.a08;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * around before 拦截谁先执行
 *
 *  注解模式中 Around 优先于执行Before的
 *
 * 	多个Before声明是怎么执行的? order接口/注解
 *
 * 	如果标记了优先级， 则Around 和  Before 的执行顺序就不定了
 *
 *
 * 	在一个aspectj中的顺序，Around 是大于Before的
 * 	但是如果横跨了多个Aspect，就不确定了 此时可能通过ordered进行来处理
 *
 *
 * methodInterceptor接口的使用
 */
@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Configuration
public class AroundBeforeAnnDemo {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(AroundBeforeAnnDemo.class, AroundBeforeAnnConfig.class);
		//applicationContext.register(AroundBeforeAnnConfig2.class); // 在增加一个aop，确定执行顺序

		applicationContext.refresh();

		AroundBeforeAnnDemo proxy = applicationContext.getBean(AroundBeforeAnnDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截
	}


	public void execute() {
		System.out.println("aroundBefore  execute()...");
	}
}
