package com.wf.postprocess.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {
	public MyMergedBeanDefinitionPostProcessor(){
		System.out.println("MyMergedBeanDefinitionPostProcessor无参构造器...");
	}


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyMergedBeanDefinitionPostProcessor 的 MergedBeanDefinitionPostProcessor 接口的 postProcessBeforeInitialization 初始化..."+bean+"=="+beanName);

		return bean; //null
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyMergedBeanDefinitionPostProcessor 的 MergedBeanDefinitionPostProcessor 接口的 postProcessAfterInitialization 初始化..."+bean+"=="+beanName);

		return null;
	}




	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		System.out.println("MyMergedBeanDefinitionPostProcessor 的 MergedBeanDefinitionPostProcessor 接口的 postProcessMergedBeanDefinition修改bean..=>"+beanName+"--"+beanType+"---"+beanDefinition);
	}

	@Override
	public void resetBeanDefinition(String beanName) {
		System.out.println("MyMergedBeanDefinitionPostProcessor 的 MergedBeanDefinitionPostProcessor 接口的 resetBeanDefinition.."+beanName);

	}
}
