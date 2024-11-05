package com.wf.xmgAop.a06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * pointCut 展示
 */
@EnableAspectJAutoProxy // 激活我们的aspect 注解自动代理
@Configuration
public class PointCutAnnoDemo {


	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext();
		applicationContext.register(PointCutAnnoDemo.class, PointCutAnnConfig.class); //  将我们的Aspect也需要进行注入 PointCutAnnConfig

		applicationContext.refresh();

		PointCutAnnoDemo proxy = applicationContext.getBean(PointCutAnnoDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截


	}

	public void execute() {
		System.out.println("execute()...");
	}


}
