package com.wf.xmgAop02.a06.a01pointcutAnn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**27
 * pointCut 展示
 * pointcut 只有过滤，没有动作，是一个评判的标准，advice才是动作
 *
 *
 *
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
		// 在屏蔽 PointCutAnnConfig中的@Before("anyPublicMethod()")时，只有判断匹配，而具体的拦截动作则是在@Before中

		/**
		 *pointcut 只有拦截过滤，没有动作
		 *
		 * around 需要手动触发
		 * Before 则是自动触发
		 *
		 *
		 *  整合： joinPoint 用来表示 连接点，spring中只有方法才是连接点 ，然后会通过pointcut 切点点进行拦截过滤我们需要的连接点，
		 *  然后通过pointcut过滤出来的joinPoint，进行执行Advice
		 *  我们可以通过Advisor 进行包装我们的joinPoint过滤逻辑和执行逻辑Advice
		 *
		 */


	}

	public void execute() {
		System.out.println("execute()...");
	}


}
