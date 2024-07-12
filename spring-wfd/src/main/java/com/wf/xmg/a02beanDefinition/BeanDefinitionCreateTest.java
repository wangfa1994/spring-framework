package com.wf.xmg.a02beanDefinition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 01 beanDefinition的创建是怎么的一个流程
public class BeanDefinitionCreateTest {

	/**
	*  BeanDefinition: bean的定义信息，我们要实例化的对象，会被解析成对应的BeanDefinition进行存储，然后通过beanDefinition进行实例化
	 *  BeanDefinition 继承了AttributeAccessor，属性访问器用来访问相关属性
	 *
	 * BeanDefinition主要包括三类: RootBeanDefinition,ChildBeanDefinition,GenericBeanDefinition .
	 * ChildBeanDefinition和GenericBeanDefinition都可以根据需要转换成RootBeanDefinition，以便于统一处理和注册到BeanFactory中
	 * 	1.RootBeanDefinition
	 * 		 * 是最完整的Bean定义类型，它可以包含关于一个Bean的所有必要信息，包括构造器参数、属性值、依赖关系、初始化方法、销毁方法等。它是定义顶级Bean的主要方式
	 * 		 * 通常用于直接定义应用程序中的Bean，或者是那些没有父Bean的Bean定义
	 * 		 * 是最全面的beanDefinition,包括所有的配置选项
	 *  2.ChildBeanDefinition
	 *  	* ChildBeanDefinition
	 * 		 * 表示一个Bean定义是另一个Bean定义的子定义。这意味着它可以从父Bean定义那里继承属性和配置信息，同时也可以覆盖或添加特定的属性
	 * 		 * 当需要创建一个与现有Bean相似但又有所不同的新Bean时使用，可以复用父Bean的配置，减少重复配置
	 * 		 * 更多的是强调继承特性
	 * 	3.GenericBeanDefinition
	 * 		* 表示一个通用的、可扩展的Bean定义类，它提供了设置各种Bean属性的方法，比如bean class、构造器参数、属性值等
	 * 		 * 相比RootBeanDefinition，它在初始化时可能不包含完整的信息，但可以根据需要动态添加。
	 * 		 * 适用于那些需要在运行时动态修改或填充Bean定义属性的场景，提供了更多的灵活性。
	 * 		 * 更多的是侧重动态配置能力
	 * 		 * 有两个子类实现 AnnotatedGenericBeanDefinition 与 ScannedGenericBeanDefinition
	 *
	 *
	 * BeanDefinition的创建主要通过两种方式：
	 * 	1. 利用BeanDefinitionBuilder进行创建
	 * 	2. 利用AbstractBeanDefinition 以及派生类进行创建
	 *
	 *
	 *
	**/

	public static void main(String[] args) {

		// beanDefinition的两种创建方式 利用BeanDefinitionBuilder进行创建
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Student.class);
		beanDefinitionBuilder.setLazyInit(false);
		beanDefinitionBuilder.addPropertyValue("name", "xmg")
							 .addPropertyValue("age", 18);

		// 利用AbstractBeanDefinition 以及派生类进行创建  ChildBeanDefinition GenericBeanDefinition RootBeanDefinition 都继承了AbstractBeanDefinition
		RootBeanDefinition 	rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.setBeanClass(Student.class);
		rootBeanDefinition.setLazyInit(false);
		MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
		mutablePropertyValues.addPropertyValue("name", "xmg01");
		mutablePropertyValues.addPropertyValue("age", 19);
		rootBeanDefinition.setPropertyValues(mutablePropertyValues);

		//
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(Student.class);
		MutablePropertyValues mutablePropertyValuesG = new MutablePropertyValues();
		mutablePropertyValuesG.add("name","xmg02")
				.add("age",20);
		genericBeanDefinition.setPropertyValues(mutablePropertyValuesG);


		/**
		 *
		 *
		 * 主要属性
		 * 1. beanClass 类的全路径名称
		 * 2. scope 作用域
		 * 3. lazyInit 是否懒加载
		 *
		 *
		 */

	}
}
