package com.wf.xmg.a09beanLifecycle.a07life;

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
		context.setConfigLocations("META-INF/a09/bean-life-09.xml");
		context.refresh();

		System.out.println("==========开始销毁容器内的userHolder");
		context.close();

		Thread.sleep(1000L);


	}

	private static void beanFactoryExecute() throws InterruptedException {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		beanFactory.addBeanPostProcessor(new MyCustomerPostProcessor());
		//beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor()); // 不能添加此后置处理器是因为他是在上下文中扩展的，不是在工厂中扩展的，而且它属于后置处理器，会在后置处理器的时候进行触发

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		String location = "META-INF/a09/bean-life-09.xml";
		reader.loadBeanDefinitions(location);


		// 在是容器的情况下，在getBean的时候才会触发相关bean的创建，而在上下文中，则是通过refresh方法进行帮我们处理的
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);


		System.out.println("==========开始销毁容器内的userHolder");
		beanFactory.destroySingleton("userHolder");

		System.out.println("==========开始销毁容器内的所有的bean");
		beanFactory.destroySingletons();

		Thread.sleep(1000L);
	}

	/**
	 * doCreateBean()方法
	 *
	 * 1.进入 InstantiationAwareBeanPostProcessor接口的 postProcessBeforeInstantiation 方法 V0---->V1
	 * createBeanInstance开始执行
	 * 2.进入 SmartInstantiationAwareBeanPostProcessor接口的 determineCandidateConstructors 方法 V1---->V2
	 * createBeanInstance执行完成
	 * 3.进入 MergedBeanDefinitionPostProcessor接口的 postProcessMergedBeanDefinition 方法 V2---->V3
	 * populateBean开始执行...
	 * 4.进入 InstantiationAwareBeanPostProcessor接口的 postProcessAfterInstantiation 方法 V3---->V4
	 * 5.进入 InstantiationAwareBeanPostProcessor接口的 postProcessProperties 方法 V4---->V5
	 * 6.进入 InstantiationAwareBeanPostProcessor接口的 postProcessPropertyValues 方法 V5---->V6
	 * populateBean执行完成...
	 * initializeBean开始执行...
	 * 7.进入 Aware相关接口的 setBeanName 方法 V6---->V7
	 * 8.进入 注解postConstruct 方法 V7---->V8
	 * 9.进入 BeanPostProcessor接口的 postProcessBeforeInitialization 方法 V8---->V9
	 * 10. 进入 InitializingBean接口的 afterPropertiesSet 方法 V9---->V10
	 * 11.进入 init-method配置的 initMethod 方法 V10---->V11
	 * 12.进入 BeanPostProcessor接口的 postProcessAfterInitialization 方法 V11---->V12
	 * initializeBean执行完成...
	 * 13.进入 DestructionAwareBeanPostProcessor接口的 requiresDestruction 方法 V12---->V13
	 * ==========开始销毁容器内的userHolder
	 * 14.进入 注解preDestroy 方法 V13---->V14
	 * 15.进入 DestructionAwareBeanPostProcessor接口的 postProcessBeforeDestruction 方法 V14---->V15
	 * 16.进入 DisposableBean接口的 destroy 方法 V15---->V16
	 * 17.进入 destroy-method配置的 destroyMethod 方法 V16---->V17
	 * ==========开始销毁容器内的所有的bean
	 *
	 */

}
