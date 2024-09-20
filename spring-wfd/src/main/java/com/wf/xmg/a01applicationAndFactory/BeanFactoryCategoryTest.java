package com.wf.xmg.a01applicationAndFactory;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

//02 BeanFactory体系是什么?有哪些分类类别
public class BeanFactoryCategoryTest {

	/**
	 *
	 *
	 * BeanFactory接口 是我们的工厂类，是容器的根接口，里面定义了获取bean的一些方法
	 *
	 * BeanFactory 主要有三个接口进行扩展
	 *
	 * 	HierarchicalBeanFactory接口扩展了BeanFactory,增加了父子关系的获取，可以存放层级相关的容器
	 * 		ConfigurableBeanFactory接口又扩展了HierarchicalBeanFactory,增加了配置bean工厂(BeanFactory)的功能，这个不打算在外部使用, 这玩意还实现了 SingletonBeanRegistry，具备注册bean实例的功能
	 * 			AbstractBeanFactory 抽象实现类，这个抽象类其实只有简单的BeanFactory功能和可配置层次关系的bean工厂
	 *
	 * 	ListableBeanFactory接口扩展了BeanFactory,增加了列表上的一些操作，在容器中一个类型可以存在多个实例，可以通过此接口进行获取
	 *
	 *  AutowireCapableBeanFactory接口扩展了BeanFactory，可以对bean进行一些自动装配的一些操作，而且这里还添加了Bean生命周期的一些相关方法定义
	 *  	AbstractAutowireCapableBeanFactory抽象实现类，继承了AbstractBeanFactory，实现了AutowireCapableBeanFactory
	 *
	 *
	 *
	 * 在这三个基础的BeanFactory 又进行了一些列的扩展
	 * 1. ConfigurableListableBeanFactory 融合了 ConfigurableBeanFactory ListableBeanFactory, AutowireCapableBeanFactory
	 * 	这个一下子全了，将上面的三个全都进行了功能扩展
	 * 		于是 DefaultListableBeanFactory就是最全面的beanFactory，从继承角度来讲只需要继承AbstractAutowireCapableBeanFactory也具备了一些通用功能
	 *
	 *
	 *BeanFactory接口定义了一个最简单的容器方法，然后通过不同的功能进行继承扩展形成不同的容器类型
	 * 最主要的一个实现类是DefaultListableBeanFactory，通过他的继承关系图，可以发现，针对列表，配置，层级都有实现,用于提供不同的功能，
	 * 是Spring IoC容器的基础组件之一。
	 *
	 */


	public static void main(String[] args) {

		DefaultListableBeanFactory  beanFactory = new DefaultListableBeanFactory();
		// 手动注册一些bean
		registerSingleton(beanFactory);

		// 父子关系查找
		lookupByParentChild(beanFactory);

		//单一类型查找
		lookupBySingleType(beanFactory);

		// 列表类型查找
		lookupByCollectionType(beanFactory);




		// 延迟查找


		//


	}

	private static void lookupByCollectionType(BeanFactory beanFactory) {
		if(beanFactory instanceof ListableBeanFactory){
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, Student> beansOfType = listableBeanFactory.getBeansOfType(Student.class);
			System.out.println("查找到的所有的 Student 集合对象：" + beansOfType);
		}
	}

	private static void lookupBySingleType(BeanFactory beanFactory) {
		Person person = beanFactory.getBean(Person.class);
		System.out.println("实时查找：" + person.getName());
	}

	private static void lookupByParentChild(DefaultListableBeanFactory beanFactory) {
		//在进行定义一个容器，形成父子关系
		// 由于 XmlBeanFactory 标记deprecation，并且spring加了校验-Werror,所以我们模仿XMlBeanFactory 自己手动实现一个XMlBeanFactory
		/*Resource resource = new ClassPathResource("/META-INF/dependency-lookup-context.xml");
		XmlBeanFactory beanFactoryParent = new XmlBeanFactory(resource);
		beanFactory.setParentBeanFactory(beanFactoryParent);
		System.out.println("从子容器中获取父容器的信息：" + beanFactory.getBean("user"));
		System.out.println("从父容器中获取对应的信信息：" + beanFactoryParent.getBean("user"));*/

		DefaultListableBeanFactory beanFactoryParent  = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactoryParent);
		reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
		beanFactory.setParentBeanFactory(beanFactoryParent);


		System.out.println("从子容器中获取父容器的信息：" + beanFactory.getBean("user"));
		System.out.println("从父容器中获取对应的信信息：" + beanFactoryParent.getBean("user"));


		/**
		 * 移除spring-bean.gradle中的 //options.compilerArgs += "-Werror"
		 * 移除 CompilerConventionsPlugin中的 "-Werror" 重新编译也是可以的，但是不建议
		 *
		 *
		 */

	}



	private static void registerSingleton(DefaultListableBeanFactory beanFactory) {
		Person person = new Person();
		person.setName("xmg");
		beanFactory.registerSingleton("person",person);

		Student student1 = new Student();
		student1.setName("xmg1");
		student1.setAge(18);
		beanFactory.registerSingleton("student1",student1);

		Student student2 = new Student();
		student2.setName("xmg2");
		student2.setAge(19);
		beanFactory.registerSingleton("student2",student2);
	}




}
