package com.wf.postprocess.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Desc :  遍历如果发现有postProcessor则会先进行实例化，遍历结束后进行排序执行
 * 第一个执行
 * 先执行BeanDefinitionRegistryPostProcessor的相关方法postProcessBeanDefinitionRegistry
 * 再执行从父接口BeanFactoryPostProcessor继承来的postProcessBeanFactory方法，
 * 然后再回执行单独实现BeanFactoryPostProcessor的相关方法
 *
 * @Author : Mr.WangF
 * @Date: 2022/7/25 18:13
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	public MyBeanDefinitionRegistryPostProcessor() {
		System.out.println("MyBeanDefinitionRegistryPostProcessor的无参构造器");
	}

	@Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor 的 BeanDefinitionRegistryPostProcessor接口的postProcessBeanDefinitionRegistry...");

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor 的 BeanFactoryPostProcessor接口的postProcessBeanFactory...");
	}

	/**
	 *
	 * 1.先遍历获取所有的 BeanDefinitionRegistryPostProcessor的name集合,遍历name进行实例化
	 * 2.按照顺序执行postProcessBeanDefinitionRegistry的方法
	 * 3.再进行执行postProcessBeanFactory的方法
	 *
	 * 4.再遍历获取所有的 BeanFactoryPostProcessor的name集合，然后遍历name封装到不同的List中，遍历list 进行对象实例化
	 * 5.再进行执行postProcessBeanFactory的方法
	 *
	 *
	 *
	 */
}
