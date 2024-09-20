package com.wf.xmg.a09beanLifecycle.a07life;

import com.wf.xmg.a09beanLifecycle.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeTest {

	public static void main(String[] args) throws InterruptedException {

		beanFactoryExecute();
		//applicationContextExecute();
	}

	private static void applicationContextExecute() throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();

		context.addBeanFactoryPostProcessor((factory)->{
			factory.addBeanPostProcessor(new MyCustomerPostProcessor());
		});
		context.setConfigLocations("META-INF/bean-life-09.xml");
		context.refresh();

		System.out.println("==========开始销毁容器内的userHolder");
		context.close();

		Thread.sleep(1000L);


	}

	private static void beanFactoryExecute() throws InterruptedException {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		beanFactory.addBeanPostProcessor(new MyCustomerPostProcessor());

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		String location = "META-INF/bean-life-09.xml";
		reader.loadBeanDefinitions(location);


		// 在是容器的情况下，在getBean的时候才会触发相关bean的创建，而在上下文中，则是通过refresh方法进行帮我们处理的
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);


		System.out.println("==========开始销毁容器内的userHolder");
		beanFactory.destroySingleton("userHolder");

		System.out.println("==========开始销毁容器内的所有的bean");
		beanFactory.destroySingletons();

		Thread.sleep(1000L);
	}
}
