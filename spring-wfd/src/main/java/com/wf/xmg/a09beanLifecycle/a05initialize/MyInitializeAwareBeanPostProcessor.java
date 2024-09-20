package com.wf.xmg.a09beanLifecycle.a05initialize;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyInitializeAwareBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("进入BeanPostProcessor的实例化之前的方法: "+beanName);


		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}



	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("进入BeanPostProcessor的实例化之后的方法: "+ beanName);

		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
