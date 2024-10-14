package com.wf.xmg.a09beanLifecycle.a07life;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
@SuppressWarnings("deprecation")
public class MyCustomerPostProcessor  implements MergedBeanDefinitionPostProcessor, SmartInstantiationAwareBeanPostProcessor, InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {


	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("1.进入 InstantiationAwareBeanPostProcessor接口的 postProcessBeforeInstantiation 方法 V0---->V1");
		return null;
	}

	@Override
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("2.进入 SmartInstantiationAwareBeanPostProcessor接口的 determineCandidateConstructors 方法 V1---->V2");
		return null;
	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		System.out.println("3.进入 MergedBeanDefinitionPostProcessor接口的 postProcessMergedBeanDefinition 方法 V2---->V3");
	}


	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("4.进入 InstantiationAwareBeanPostProcessor接口的 postProcessAfterInstantiation 方法 V3---->V4");
		return true;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		System.out.println("5.进入 InstantiationAwareBeanPostProcessor接口的 postProcessProperties 方法 V4---->V5");
		return null;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		System.out.println("6.进入 InstantiationAwareBeanPostProcessor接口的 postProcessPropertyValues 方法 V5---->V6");
		return pvs;
	}


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("9.进入 BeanPostProcessor接口的 postProcessBeforeInitialization 方法 V8---->V9");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("12.进入 BeanPostProcessor接口的 postProcessAfterInitialization 方法 V11---->V12");
		return bean;
	}
	@Override
	public boolean requiresDestruction(Object bean) {
		System.out.println("13.进入 DestructionAwareBeanPostProcessor接口的 requiresDestruction 方法 V12---->V13");
		return true;
	}

	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {

		System.out.println("15.进入 DestructionAwareBeanPostProcessor接口的 postProcessBeforeDestruction 方法 V14---->V15");
	}









	@Override
	public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("不知道怎么进入的 predictBeanType 方法 ：在上下文中ApplicationContext中使用");
		// org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.predictBeanType
		// 解决依赖的时候有使用 org.springframework.beans.factory.support.AbstractBeanFactory.getType(java.lang.String, boolean)
		return null;
	}

	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		System.out.println("不知道怎么进入的 getEarlyBeanReference 方法 :在解决循环依赖中使用");
		return bean;
	}



	@Override
	public void resetBeanDefinition(String beanName) {
		System.out.println("不知道怎么进入的 resetBeanDefinition 方法 V0---->V1");
	}
}
