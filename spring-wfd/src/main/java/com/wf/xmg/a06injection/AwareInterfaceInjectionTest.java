package com.wf.xmg.a06injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// 接口回调注入
public class AwareInterfaceInjectionTest {

	public static void main(String[] args) {

		// 创建 BeanFactory 容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册 Configuration Class（配置类） -> Spring Bean
		context.register(AwareInterfaceInjectionTest.class);
		// 手动注册我们的bean
		context.register(AwareDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		AwareDemo awareDemo = context.getBean(AwareDemo.class);

		// 通过相关的Aware接口，通过回调进行相关依赖的注入
		System.out.println(AwareDemo.beanFactory == context.getBeanFactory());
		System.out.println(awareDemo.applicationContext == context);

		// 显示地关闭 Spring 应用上下文
		context.close();
	}




	static class AwareDemo implements BeanFactoryAware, ApplicationContextAware {

		private static BeanFactory beanFactory;

		private  ApplicationContext applicationContext;



		@Override
		public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
			AwareDemo.beanFactory = beanFactory;
		}

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			//this.applicationContext = applicationContext;
			this.applicationContext = applicationContext;
		}
	}
}
