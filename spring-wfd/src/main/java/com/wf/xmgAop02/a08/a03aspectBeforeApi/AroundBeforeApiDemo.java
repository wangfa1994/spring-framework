package com.wf.xmgAop02.a08.a03aspectBeforeApi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 35 API 实现BeforeAdvice
 *  *
 *  * 核心接口 BeforeAdvice  标记接口类似Advice
 *  *
 *  * 方法JointPoint扩展  MethodBeforeAdvice
 *  *
 *  * 接收对象 AdvisedSupport
 *  * 	基础实现类 ProxyCreatorSupport
 *  * 		常见实现类 proxyFactory ProxyFactoryBean AspectJProxyFactory
 *
 */
public class AroundBeforeApiDemo {

	// XML的顺序 并不是写死的，会根据配置的顺序进行调增
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("classpath:/META-INF/aop02/a08/beforeAround.xml");

		applicationContext.refresh();

		AroundBeforeApiDemo proxy = applicationContext.getBean(AroundBeforeApiDemo.class);
		proxy.execute(); // 执行的时候会被我们的拦截器拦截


	}

	public void execute() {
		System.out.println("xml execute()...");
	}
}
