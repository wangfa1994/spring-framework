package com.wf.xmgAop03.a04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 50 课 springAop joinPoint beforeAdvice的aspectJ实现
@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Configuration
public class AspectJMethodBeforeAdviceDemo {

	// AspectJMethodBeforeAdvice

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(AspectJMethodBeforeAdviceDemo.class, MethodBeforeAdviceAnnConfig.class); //  将我们的Aspect也需要进行注入 PointCutAnnConfig

		applicationContext.refresh();

		AspectJMethodBeforeAdviceDemo proxy = applicationContext.getBean(AspectJMethodBeforeAdviceDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截
	}

	public void execute() {
		System.out.println("execute()...");
	}

}
