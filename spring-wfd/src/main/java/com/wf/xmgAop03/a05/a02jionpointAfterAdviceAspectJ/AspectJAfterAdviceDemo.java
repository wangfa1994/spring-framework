package com.wf.xmgAop03.a05.a02jionpointAfterAdviceAspectJ;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/** 51   joinPoint after advice Aspectj实现
 *
 * 接口
 * @see org.springframework.aop.AfterAdvice
 * @see org.springframework.aop.AfterReturningAdvice
 * @see org.springframework.aop.ThrowsAdvice
 *
 * 实现
 * @see org.springframework.aop.aspectj.AspectJAfterAdvice
 * @see org.springframework.aop.aspectj.AspectJAfterReturningAdvice
 * @see org.springframework.aop.aspectj.AspectJAfterThrowingAdvice
 *
 * */

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
