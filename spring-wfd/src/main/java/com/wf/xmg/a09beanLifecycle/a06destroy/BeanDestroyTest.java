package com.wf.xmg.a09beanLifecycle.a06destroy;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

// bean的销毁阶段
public class BeanDestroyTest {


	public static void main(String[] args) throws InterruptedException {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		String location = "META-INF/bean-destroy-09.xml";
		reader.loadBeanDefinitions(location);


		// 在是容器的情况下，在getBean的时候才会触发相关bean的创建，而在上下文中，则是通过refresh方法进行帮我们处理的
		/*UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		String stringDemo = beanFactory.getBean("stringDemo",String.class);*/

		beanFactory.preInstantiateSingletons(); //或者利用此方法也可以直接进行实例化容器中所有beanDefinition，上下文ApplicationContext就是这么弄的


		System.out.println("==========开始销毁容器内的userHolder");
		beanFactory.destroySingleton("userHolder");

		System.out.println("==========开始销毁容器内的所有的bean");
		beanFactory.destroySingletons();

		Thread.sleep(1000L);

	}
}
