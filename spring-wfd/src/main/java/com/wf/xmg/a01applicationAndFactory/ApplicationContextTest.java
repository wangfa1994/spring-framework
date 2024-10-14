package com.wf.xmg.a01applicationAndFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 01 ApplicationContext体系是什么?有哪些分类类别,与BeanFactory体系有什么区别?
public class ApplicationContextTest {

	/**
	 * ApplicationContext接口可以说是容器上层的应用，因为他继承了ListableBeanFactory和HierarchicalBeanFactory接口，
	 * 除了提供BeanFactory对容器的管理之外，还在其中添加了一些针对企业级应用的功能，国际化、事件发布、资源加载等
	 * 而且还需要注意的是，ApplicationContext的抽象子类AbstractRefreshableApplicationContext中，内置了一个BeanFactory的实现DefaultListableBeanFactory
	 * 是否存在一种代理感觉呢？
	 *
	 * 最常用的两个上下文：ClassPathXmlApplicationContext 与 AnnotationConfigApplicationContext 类
	 *
	 *
	 * ConfigurableApplicationContext接口
	 * 		AbstractApplicationContext
	 * 				AbstractRefreshableApplicationContext
	 * 				GenericApplicationContext
	 *
	 * WebApplicationContext接口
	 *
	 */

	public static void main(String[] args) {

		/**
		 * BeanFactory接口与ApplicationContext接口
		 *
		 * BeanFactory是Spring IoC容器的最基本实现。它主要负责管理bean的生命周期，依赖注入，bean的实例化和配置等相关功能。提供了一些基本功能
		 * 相对比较简单。它使用懒加载的机制，只有在请求的时候getBean才会进行实例化我们的Bean。
		 *
		 * ApplicationContext接口是BeanFactory接口的子接口，它扩展了BeanFactory，添加了一些企业级应用需要的功能。
		 * 	- 继承了ListableBeanFactory的所有功能
		 * 	- 提供国际化的支持  通过MessageSource
		 * 	- 支持事件发布    通过ApplicationEventPublisher
		 * 	- 更方便的资源访问，如加载文件资源  通过ResourceLoader
		 * 	- 支持应用层特定的上下文，如WebApplicationContext
		 * 	- 自动注册BeanPostProcessor和BeanFactoryPostProcessor
		 * 	- 支持更多的注解
		 * 	需要的注意的是ApplicationContext通常在启动时就实例化所有单例bean，而不是等到请求时再实例化
		 *
		 *
		 *
		 *
		 */




	}
}
