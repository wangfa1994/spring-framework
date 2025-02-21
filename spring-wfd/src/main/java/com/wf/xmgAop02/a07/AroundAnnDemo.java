package com.wf.xmgAop02.a07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Configuration
public class AroundAnnDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(AroundAnnDemo.class, AroundAnnConfig.class);

		applicationContext.refresh();

		AroundAnnDemo proxy = applicationContext.getBean(AroundAnnDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截
		// AroundAnnConfig 中around 通知的ProceedingJoinPoint的执行与否，打印信息不一致 around 需要我们手动触发
	}

	public void execute() {
		System.out.println("around api execute()...");
	}
}
