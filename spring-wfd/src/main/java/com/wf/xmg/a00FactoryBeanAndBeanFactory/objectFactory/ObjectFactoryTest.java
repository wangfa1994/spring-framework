package com.wf.xmg.a00FactoryBeanAndBeanFactory.objectFactory;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

public class ObjectFactoryTest {
	/**
	 *
	 * ObjectFactory 此接口通常用于封装泛型工厂，该工厂在每次调用时返回某个目标对象的新实例(原型)。 包装成一个工厂，可以用来得到不同的对象
	 *
	 * 也可以进行延迟获取对象，或者在某些特定场景下动态创建对象
	 *
	 * spring的Bean参与生命周期， ObjectFactory 没有参与生命周期
	 *
	 *
	 */

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);
		scanner.scan("com.wf.xmg.a00FactoryBeanAndBeanFactory.objectFactory");
		scanner.setIncludeAnnotationConfig(false);


		RootBeanDefinition definition = new RootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class);
		beanFactory.registerBeanDefinition(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, definition);

		beanFactory.addBeanPostProcessor(beanFactory.getBean(AutowiredAnnotationBeanPostProcessor.class));

		//使用工具类进行注册关于注解的Processors
		//AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
		ObjectFactoryAutoContainDto bean = beanFactory.getBean(ObjectFactoryAutoContainDto.class);
		System.out.println(bean.getSchoolObjectFactory());


		// 也可以直接从容器中得到 SchoolObjectFactory
		SchoolObjectFactory schoolObjectFactory = beanFactory.getBean(SchoolObjectFactory.class);
		System.out.println(schoolObjectFactory);
		System.out.println(schoolObjectFactory.getObject());
		System.out.println(schoolObjectFactory.getObject());
		System.out.println(schoolObjectFactory.getObject());


	}
}
