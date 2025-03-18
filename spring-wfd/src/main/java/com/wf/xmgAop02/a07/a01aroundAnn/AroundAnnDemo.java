package com.wf.xmgAop02.a07.a01aroundAnn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * 30 @AspectJ拦截动作  @Around 和 @pointcut(这个注解是aspectJ的) 有区别吗？
 *
 * @pointcut注解只是判断，没有动作，过滤类和方法，而且是aspectJ中的注解
 */

@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Configuration
public class AroundAnnDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(AroundAnnDemo.class, AroundAnnConfig.class); // 配置中增加了 aroundAnyPublicMethod

		applicationContext.refresh();

		AroundAnnDemo proxy = applicationContext.getBean(AroundAnnDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截
		// AroundAnnConfig 中around 通知的ProceedingJoinPoint的执行与否，打印信息不一致 around 需要我们手动触发
	}

	public void execute() {
		System.out.println("around api execute()...");
	}
}
