package com.wf.model.beanFactory;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class BeanFactoryTest {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		BeanDefinitionBuilder catBuilder = BeanDefinitionBuilder.genericBeanDefinition(Cat.class);
		catBuilder.addPropertyValue("category","jiafei");
		AbstractBeanDefinition catBuilderBeanDefinition = catBuilder.getBeanDefinition();


		BeanDefinitionBuilder personBuilder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
		personBuilder.addPropertyReference("cat","cat");
		AbstractBeanDefinition personBuilderBeanDefinition = personBuilder.getBeanDefinition();
		//personBuilderBeanDefinition.setInstanceSupplier(); 自定义创建我们的实例
		//personBuilderBeanDefinition.setFactoryMethodName("");


		beanFactory.registerBeanDefinition("cat",catBuilderBeanDefinition);
		beanFactory.registerBeanDefinition("person",personBuilderBeanDefinition);
		// 此时的getBean主流程中，没有一些对应的处理器，此时的流程是最简单最纯粹的
		Cat cat = beanFactory.getBean("cat",Cat.class);

		Person person = beanFactory.getBean("person",Person.class);
		System.out.println("cat: "+cat);

		System.out.println("person: "+person);

	}

	/**
	 容器BeanFactory在getBean的过程中实际就是得到或者创建Bean实例的过程
	 首选解析转换我们传入的bean名称，因为不确定是是否是FactoryBean还是普通的bean
	 然后根据解析到的bean名称从bean实例的注册中心确定是否已经存在对应的实例，存在直接返回
	 不存在的话确定是否存在父类工厂，从父类工厂中获取对象试试看， 如果能从父类获取到则也返回
	 不存在的话，我们进行beanDefinition的转换与合并，进行不同类型的BeanDefinition转换成RootBeanDefinition,进行父子关系的合并
	 然后从我们合并之后的RootBeanDefinition确定是否存在依赖项，如果存在需要先进行依赖项的创建，然后在接着创建我们本体bean实例
	 利用bean实例的注册中心的获取bean实例的重载方法，设置对象创建中标志，并切通过ObjectFactory的函数方法回调到BeanFactory中进行回调创建对象。
	 开始创建bean对象，根据beanDefinition先解析出要创建bean对象的类，beanClass类型是Object的，在注解和xml模式下，可能是类也可能是字符串.
	 此时我们再进行InstantiationAwareBeanPostProcessor的前置方法回调，确定下用户是否自己返回了创建的对象，
	 如果用户自己创建了对象，则再进行一下BeanPostProcessor的后置方法postProcessAfterInitialization调用，然后直接返回对象，
	 否则spring开始进行默认bean的创建，从beanDefinition中看看是否设置了instanceSupplier，是否设置了factoryMethodName.
	 我们开始先确定构造器，通过对应的SmartInstantiationAwareBeanPostProcessor进行判断是否指定了构造器，
	 如果指定了就通过委派给ConstructorResolver进行指定的构造器实例化和依赖注入，没有指定的话就进行无参构造器实例化，最后返回被包装的BeanWrapper。
	 实例创建完成之后，我们需要先进行我们的BeanDefinition的合并，这个合并主要用于依赖注入相关属性的解析。然后再把我们的bean实例提前暴露到三级缓存中
	 属性解析完成之后开始进行我们的属性赋值。赋值之前我们先通过InstantiationAwareBeanPostProcessor的后置方法进行是否需要赋值。如果不需要的话直接返回
	 在需要的情况下，我们从beanDefinition中获取到属性值，然后确定是否有自动以来注入模式(autowireByName/autowireByType)进行对应的属性值解析。
	 然后我们再次进行InstantiationAwareBeanPostProcessor的属性设置回调方法，是否进行了相关属性值的覆盖计算。
	 然后我们得到我们的依赖属性值，开始了我们真正的属性设置，赋值的过程中可能会循环多次调用getBean来获取依赖的属性值。
	 最后开始进行我们的实例化bean。执行相关的一些方法，首先执行Aware接口相关的逻辑，然后执行BeanPostProcessor的前置方法，然后执行InitializingBean接口逻辑
	 然后执行自定义的init-method的方法，然后执行BeanPostProcessor的后置方法
	 实例化之后开始进行判断是否允许提前暴露，进行三级缓存升级到二级缓存中。
	 然后将我们的bean注册为一次性的，并进行注册我们的销毁回调
	 开始善后工作，singletonsCurrentlyInCreation进行移除创建中标志。
	 最后将我们的对象放入到一级缓存中并且移除掉二级和三级缓存
	 */
}
