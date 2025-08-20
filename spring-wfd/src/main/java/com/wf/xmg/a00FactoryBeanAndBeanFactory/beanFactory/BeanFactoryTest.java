package com.wf.xmg.a00FactoryBeanAndBeanFactory.beanFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import org.springframework.core.ResolvableType;

public class BeanFactoryTest {
	/**
	 * BeanFactory 只是一个工厂接口，从工厂中得到我们的bean,但是我们的bean怎么来的，是否需要注册中心,是否存在其他方法进行转换产生bean实例
	 *
	 * DefaultListableBeanFactory是重要的一个工厂实现
	 *
	 * DefaultListableBeanFactory体系
	 *
	 * 	BeanFactory接口:定义了一些得到bean的方法，以及一些属性的获取
	 * 		AutowireCapableBeanFactory接口:
	 *
	 *
	 *
	 *
	 *
	 *
	 */


	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		//  ClassPathBeanDefinitionScanner 定义一个类路径下的beanDefinition扫描器，将扫描的BeanDefinition，进行注册到BeanDefinitionRegistry
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory); // beanFactory的身份是BeanDefinitionRegistry，bd注册器
		//scanner.setIncludeAnnotationConfig(false);//取消掉对应的关于注解的相关配置，这样的话，就不会进行autowireBeanPostProcess等相关的注册,

		scanner.scan("com.wf.xmg.a00FactoryBeanAndBeanFactory.beanFactory"); // 扫描此包下面的,进行注册，注册到我们的工厂中，然后再从工厂中得到


		// 针对于DefaultListableBeanFactory 容器来讲，只有在getBean的时候才会进行实例化,所以后来延伸出来上下文，直接进行前置加载
		Student student = beanFactory.getBean("student",Student.class);
		System.out.println("sutdent: "+student);

		/**
		 * BeanFactory 只是一个标准的工厂，从这个工厂中获取我们的bean,但是这个bean怎么来的，怎么处理的，却没有讲，我们可以很简单的实现，但是这样没有意义
		 * spring 将 BeanFactory 进行了丰富的实现，从哪个获得工厂产生的bean，怎么获得，等一系列进行了丰富的实现
		 *
		 */

	}
	/*我们自己实现的，只不过没有spring的生态好，因为这个是写死的，spring进行了从哪里得到对象，如何得到对象，对象之间的依赖怎么处理，形成了一套完整的体系*/
	static class MyBeanFactory implements BeanFactory {

		@Override
		public Object getBean(String name) throws BeansException {
			if(name.equals("BMW")){
				return "大奔产品";
			}else {
				return "其他产品";
			}

		}

		@Override
		public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
			return null;
		}

		@Override
		public Object getBean(String name, Object... args) throws BeansException {
			return null;
		}

		@Override
		public <T> T getBean(Class<T> requiredType) throws BeansException {
			return null;
		}

		@Override
		public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
			return null;
		}

		@Override
		public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
			return null;
		}

		@Override
		public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
			return null;
		}

		@Override
		public boolean containsBean(String name) {
			return false;
		}

		@Override
		public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
			return false;
		}

		@Override
		public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
			return false;
		}

		@Override
		public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
			return false;
		}

		@Override
		public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
			return false;
		}

		@Override
		public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
			return null;
		}

		@Override
		public Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException {
			return null;
		}

		@Override
		public String[] getAliases(String name) {
			return new String[0];
		}
	}
}
