package com.wf.xmgAop03.a04.a02beforeAdvice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//重点 50 课 JoinPointBeforeAdvice 的 aspectJ实现
 // 实现类 AspectJMethodBeforeAdvice

/**
 *
 * @see org.springframework.aop.aspectj.AspectJMethodBeforeAdvice
 */

@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Configuration
public class A02AspectJMethodBeforeAdviceDemo {

	// AspectJMethodBeforeAdvice

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(A02AspectJMethodBeforeAdviceDemo.class, MethodBeforeAdviceAnnConfig.class); //  将我们的Aspect也需要进行注入 PointCutAnnConfig

		applicationContext.refresh();

		A02AspectJMethodBeforeAdviceDemo proxy = applicationContext.getBean(A02AspectJMethodBeforeAdviceDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截
		// MethodBeforeAdviceInterceptor
	}

	public void execute() {
		System.out.println("execute()...");
	}



}
