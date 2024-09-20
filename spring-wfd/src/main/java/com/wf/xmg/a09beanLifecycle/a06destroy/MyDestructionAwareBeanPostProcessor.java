package com.wf.xmg.a09beanLifecycle.a06destroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {


	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {

		System.out.println("进入DestructionAwareBeanPostProcessor的销毁的方法: "+beanName);

	}
}
