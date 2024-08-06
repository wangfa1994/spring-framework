package com.wf.xmg.a09lifecycle.a05initialize;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 属性填充之后，开始进行对应的初始化 初始化包括 aware接口 初始化前，初始化，初始化后操作
public class BeanInitializeTest {

	/**
	 * Aware接口(针对于bean实现)
	 * 属性填充完成之后，会针对一些特殊的属性进行填充，此时会先进行相关Aware接口的执行，
	 * 实例化和初始化是针对所有的bean创建过程进行拦截(通用性)，但是Aware接口则是针对某一个bean的自己的处理(特殊性)
	 * 如果我们要创建的bean实现了相关Aware接口，则表明这个bean想要从容器或者上下文中获取一些属性
	 * 需要注意的是容器的Aware接口和上下文的Aware接口是完全不同的，容器的Aware接口是针对容器的，上下文的Aware接口是针对上下文的，、
	 * 容器的Aware接口是针对容器的， 是在AbstractAutowireCapableBeanFactory#invokeAwareMethods中进行处理的。
	 * 针对上下文的Aware接口的处理在 ApplicationContextAwareProcessor中postProcessBeforeInitialization处理的,即初始化之前进行处理的，属于BeanPostProcessor的拦截处理。
	 * 先处理容器自己的Aware接口，然后利用BeanPostProcessor拦截器处理上下文的Aware接口
	 *
	 *
	 * 初始化前
	 * aware接口执行完毕之后，开始执行我们的 初始化前 操作
	 * 利用了 BeanPostProcessor的postProcessBeforeInitialization方法进行相关处理
	 * 上下文中的Aware接口 ApplicationContextAwareProcessor 就是在这里处理的。
	 * 注解@PostConstruct  CommonAnnotationBeanPostProcessor 也是在这里处理的
	 *
	 *
	 * 初始化中(针对于bean的实现)
	 *
	 * 开始执行我们的初始化相关的操作
	 * 先利用InitializingBean的afterPropertiesSet方法进行相关处理
	 * 然后再进行我们的自定义方法进行执行init-method逻辑
	 *
	 * 初始化后
	 * 利用了 BeanPostProcessor的postProcessAfterInitialization方法进行相关处理
	 *
	 *
	 *
	 *
	 *
	 */
	public static void main(String[] args) {

		System.out.println("beanFactoryCreate ============================================");
		beanFactoryCreate();
		System.out.println("applicationContextCreate ============================================");
		applicationContextCreate();

	}

	private static void applicationContextCreate() {

		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/bean-initialize-09.xml");
		context.getBeanFactory().addBeanPostProcessor(new MyInitializeAwareBeanPostProcessor()); context.refresh(); 这种方式为什么不行*/

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.addBeanFactoryPostProcessor(beanFactory-> beanFactory.addBeanPostProcessor( new MyInitializeAwareBeanPostProcessor()));
		context.setConfigLocation("META-INF/bean-initialize-09.xml");
		context.refresh();

		UserHolder userHolder = context.getBean("userHolder", UserHolder.class);

		System.out.println("userHolder.beanName: "+userHolder.beanName);
		System.out.println("userHolder.classLoader: "+userHolder.classLoader);
		System.out.println("userHolder.beanFactory: "+userHolder.beanFactory);
		System.out.println("context.beanFactory == userHolder.beanFactory: "+(context.getBeanFactory() == userHolder.beanFactory));

		System.out.println("userHolder.environment: "+userHolder.environment);
		System.out.println("userHolder.applicationContext: "+userHolder.applicationContext);

		System.out.println("context == userHolder.context: "+(context == userHolder.applicationContext));

	}

	private static void beanFactoryCreate() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(null)); 我们不能再通过此方式进行注入我们的上下文Aware拦截器了，为什么呢？

		beanFactory.addBeanPostProcessor(new MyInitializeAwareBeanPostProcessor());

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		String location = "META-INF/bean-initialize-09.xml";
		reader.loadBeanDefinitions(location); // 在进行加载beanDefinition的时候，激活了注解相关的beanDefinition，但是为什么没有走到我们的@PostConstruct注解呢？利用上下文就可以呢


		// 因为上下文有将我们的处理器的BeanDefinition,变成实例的操作，但是我们的容器没有，除非我们手动调用下，将我们相关的BeanDefinition变成实例，然后进行相关的处理
		//beanFactory.addBeanPostProcessor((BeanPostProcessor) beanFactory.getBean(AnnotationConfigUtils.COMMON_ANNOTATION_PROCESSOR_BEAN_NAME));
		//beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor()); //或者直接注入


		// 在是容器的情况下，在getBean的时候才会触发相关bean的创建，而在上下文中，则是通过refresh方法进行帮我们处理的
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

		System.out.println("userHolder.beanName: "+userHolder.beanName);
		System.out.println("userHolder.classLoader: "+userHolder.classLoader);
		System.out.println("userHolder.beanFactory: "+userHolder.beanFactory);
		System.out.println("beanFactory == userHolder.beanFactory: "+(beanFactory == userHolder.beanFactory));
	}
}
