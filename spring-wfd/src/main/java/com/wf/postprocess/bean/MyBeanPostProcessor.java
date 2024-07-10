package com.wf.postprocess.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


/**
 * Bean组件的 PostProcessor
 *
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
	public MyBeanPostProcessor(){
		System.out.println("MyBeanPostProcessor...");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("执行MyBeanPostProcessor...BeanPostProcessor接口的 postProcessBeforeInitialization..."+bean+"==>"+beanName);

		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessor...BeanPostProcessor接口的 postProcessAfterInitialization..."+bean+"==>"+beanName);

		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

	/**
	 * 系统会给我们注册一个处理Aware接口的ApplicationContextAwarePostProcessor并且放到第一个执行
	 * 1.遍历执行所有的postProcessor,执行 postProcessBeforeInitialization方法
	 *    1.1ApplicationContextAwarePostProcessor中会处理Aware接口，通过回调调到我们对应的方法中
	 *    1.2执行我们自定义的postProcessBeforeInitialization方法
	 * 2.invokeInitMethods 方法 执行InitializingBean方法的afterPropertiesSet方法
	 *
	 * 3. 遍历执行所有的postProcessor,执行 postProcessAfterInitialization方法
	 *
	 *
	 *
	 * 每创建一个对象都会进行遍历PostProcessor
	 *
	 *
	 *
	 * BeanPostProcessor 与 XXXAware 与  InitializingBean的区别
	 *
	 * BeanPostProcessor接口主要在bean实例创建createBeanInstance，bean属性赋值populateBean，bean的对象初始化initializeBean阶段进行bean的相关增强，
	 *
	 * XXXAware接口主要是给我们的bean注入系统相关属性，用于获取系统内置的一些对象，比如ApplicationContextAware，EnvironmentAware,BeanFactoryAware等
	 * 但是需要注意的是，只实现Aware接口是没用的，系统中解析Aware也是通过后置处理器ApplicationContextAwareProcessor进行处理的。
	 *
	 * InitializingBean接口则是在所有的属性都赋值完之后进行执行的(执行在BeanPostProcessor的Before之后)，主要用于检测属性的必要性是否都已经赋值之类的校验。和我们的xml定义的init-method一致。
	 *
	 *
	 *
	 *
	 */


	/**
	 *Bean的后置处理器BeanPostProcessor，
	 * 主要在bean实例创建createBeanInstance，bean属性赋值populateBean，bean的对象初始化initializeBean阶段进行bean的相关增强，主要是增强bean的。
	 *
	 *1. BeanPostProcessor
	 * 	通过BeanPostProcessor可以用来修改bean的instance
	 * 		通常填充bean属性populateBean,实现postProcessBeforeInitialization方法，此方法会执行在bean创建之后，而且在任何实例化initialize之前，InitializingBean和init-method之前
	 * 	    通常要返回一个代理bean对象，实现postProcessAfterInitialization方法，此方法会执行在bean创建之后，而且在任何实例化initialize之后，InitializingBean和init-method之后
	 *
	 *
	 *
	 *
	 *
	 *
	 * 	 * ApplicationContextAwareProcessor
	 * 	 * ApplicationListenerDetector
	 */
}
