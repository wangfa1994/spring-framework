package com.wf.postprocess.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Desc :第一个执行
 *
 * @Author : Mr.WangF
 * @Date: 2022/7/26 10:31
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public MyBeanFactoryPostProcessor() {
		System.out.println("MyBeanFactoryPostProcessor的无参构造器...");
	}

	@Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor 的 BeanFactoryPostProcessor接口的postProcessBeanFactory...");

	}

	/**
	 *
	 *
	 * BeanFactory的后置处理器 ，与bean定义进行交互。用于实例化bean执行之前，新增beanDefinition,改变beanDefinition的属性，
	 * 	 * 	BeanDefinitionRegistryPostProcessor ：主要用来处理新增，修改我们工厂中的beanDefinition,经典实用案例：ConfigurationClassPostProcessor
	 * 	 * 	BeanFactoryPostProcessor ：主要用来处理我们的beanFactory的属性， ,经典实用案例 PropertyResourceConfigurer
	 *
	 *
	 *
	 */
}
