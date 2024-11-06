package com.wf.xmgAop03.a05;

import com.wf.xmgAop03.a04.AspectJMethodBeforeAdviceDemo;
import com.wf.xmgAop03.a04.MethodBeforeAdviceAnnConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 52课  spring 适配AspectJ的实现  ====> spring aop的实现
@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Configuration
public class AspectJAfterAdviceDemo {

	/**
	 *   AspectJAfterAdvice
	 *
	 *   AspectJAfterReturningAdvice
	 *
	 *   AspectJAfterThrowingAdvice
	 *
	 *
	 */
	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(AspectJAfterAdviceDemo.class, AfterAdviceConfig.class); //  将我们的Aspect也需要进行注入 PointCutAnnConfig

		applicationContext.refresh();

		AspectJAfterAdviceDemo proxy = applicationContext.getBean(AspectJAfterAdviceDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截
	}

	public void execute() {
		System.out.println("execute()...");
	}


}
