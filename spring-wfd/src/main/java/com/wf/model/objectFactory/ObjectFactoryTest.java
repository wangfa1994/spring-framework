package com.wf.model.objectFactory;


import com.wf.model.factoryBean.StudentFactoryBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	 * ObjectFactory 是否可以用在解决循环依赖的方面呢
	 *
	 * ObjectFactory 的依赖处理是在 DefaultListableBeanFactory中进行处理的,在处理依赖的时候会将ObjectFactory和ObjectProvider进行相同的处理，
	 * 然后再进行调用getObject方法的时候实际上是调用了DefaultListableBeanFactory.DependencyObjectProvider，在这里会从容器中获取到我们真正的对象。
	 * DefaultListableBeanFactory#resolveDependency()
	 *
	 * spring针对ObjectFactory的对象返回的其实是一个DependencyObjectProvider对象，然后我们再进行getObject的时候，才会进行从容器中查询我们真正的对象
	 */

	@Autowired
	private ObjectFactory<DefaultObject> defaultObjectObjectFactory; // 默认的ObjectFactory,是DefaultListableBeanFactory内部类DependencyObjectProvider，而不是代理对象



	public static void main(String[] args) throws Exception {

		applicationContextCase();
	}

	private static void applicationContextCase() throws Exception {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ObjectFactoryTest.class);

		ObjectFactoryTest objectFactoryTest = applicationContext.getBean(ObjectFactoryTest.class);
		ObjectFactory<DefaultObject> defaultObjectObjectFactory1 = objectFactoryTest.defaultObjectObjectFactory;
		// 上下文管理的对象
		DefaultObject defaultObject1 = defaultObjectObjectFactory1.getObject();
		System.out.println("通过默认的注入的方法进行获取对象1:"+defaultObject1);
		DefaultObject defaultObject2 = defaultObjectObjectFactory1.getObject();
		System.out.println("通过默认的注入的方法进行获取对象2:"+defaultObject2);
		System.out.println("从默认的objectFactory中得到的对象是同一个吗？"+(defaultObject1 == defaultObject2));

		// 自定义管理的对象
		/*SchoolObjectFactory schoolObjectFactory = applicationContext.getBean("schoolObjectFactory", SchoolObjectFactory.class);
		School school1 = schoolObjectFactory.getObject();

		System.out.println("school1: "+school1);
		School school2 = schoolObjectFactory.getObject();
		System.out.println("school2: "+school2);

		System.out.println("从同一个自定义的objectFactory中得到的对象是同一个吗？"+(school1 == school2));*/

		// 为什么我们自定义的多次获取都取到了不同的对象，而用spring管理的却得到的是同一个对象。 spring的是每次都从容器中获取的，当然是同一个了，


	}


	private static void beanFactoryCase() throws Exception {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(defaultListableBeanFactory);

		reader.register(SchoolObjectFactory.class); // 这里只是会把StudentFactoryBean解析成beanDefinition

		SchoolObjectFactory bean = defaultListableBeanFactory.getBean(SchoolObjectFactory.class);
		School object = bean.getObject();
	}
}
