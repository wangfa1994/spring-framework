package com.wf.xmg.a00FactoryBeanAndBeanFactory.objectProvider;

import com.wf.xmg.a00FactoryBeanAndBeanFactory.objectFactory.ObjectFactoryAutoContainDto;
import com.wf.xmg.a00FactoryBeanAndBeanFactory.objectFactory.SchoolObjectFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class ObjectProviderTest {
	/**
	 *
	 * ObjectProvider 扩展了ObjectFactory,新增了带参数的，还进行了支持表达式
	 *
	 *
	 */

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);
		scanner.scan("com.wf.xmg.a00FactoryBeanAndBeanFactory.objectProvider");
		scanner.setIncludeAnnotationConfig(false);


		RootBeanDefinition definition = new RootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class);
		beanFactory.registerBeanDefinition(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, definition);

		beanFactory.addBeanPostProcessor(beanFactory.getBean(AutowiredAnnotationBeanPostProcessor.class));

		//使用工具类进行注册关于注解的Processors
		//AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
		ObjectProviderContainDto bean = beanFactory.getBean(ObjectProviderContainDto.class);
		System.out.println(bean.getPersonObjectProvider());


		// 也可以直接从容器中得到 SchoolObjectFactory
		PersonObjectProvider personObjectProvider = beanFactory.getBean(PersonObjectProvider.class);
		System.out.println(personObjectProvider);
		System.out.println(personObjectProvider.getObject());
		System.out.println(personObjectProvider.getObject());
		System.out.println(personObjectProvider.getObject());


	}
}
