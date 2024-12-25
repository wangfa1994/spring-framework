package com.wf.model.objectFactory;


import com.wf.model.factoryBean.StudentFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

// ObjectFactory 流程   对比 FactoryBeanTest
@ComponentScan(basePackages = {"com.wf.model.objectFactory"})
public class ObjectFactoryTest {
	/**
	 *
	 * ObjectFactory<T> 是一个接口，它提供了一种延迟获取对象实例的方式。
	 * 它只有一个方法 T getObject()，该方法返回由工厂创建的对象实例。
	 * 使用场景：当你需要延迟加载某个对象或者在运行时根据条件动态获取对象时，可以使用 ObjectFactory。
	 * 它通常用于依赖注入时，允许你在真正需要对象的时候才去创建或获取它，而不是在容器初始化时就创建
	 */

	public static void main(String[] args) throws Exception {

		applicationContextCase();
	}

	private static void applicationContextCase() throws Exception {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ObjectFactoryTest.class);
		SchoolObjectFactory schoolObjectFactory = applicationContext.getBean("schoolObjectFactory", SchoolObjectFactory.class);
		School object = schoolObjectFactory.getObject();

		System.out.println("school: "+object);
		School object1 = schoolObjectFactory.getObject();
		System.out.println("school: "+object1);
	}


	private static void beanFactoryCase() throws Exception {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(defaultListableBeanFactory);

		reader.register(SchoolObjectFactory.class); // 这里只是会把StudentFactoryBean解析成beanDefinition

		SchoolObjectFactory bean = defaultListableBeanFactory.getBean(SchoolObjectFactory.class);
		School object = bean.getObject();
	}
}
