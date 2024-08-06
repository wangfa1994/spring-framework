package com.wf.xmg.a03singletonBean.specialFactoryBean;


import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;
@SuppressWarnings({"deprecation", "unchecked"})
public class SpecialSingletonBeanInstantiationTest {

	public static void main(String[] args) {

		// 特殊的bean创建流程
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
		// 通过 ApplicationContext 获取 AutowireCapableBeanFactory
		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

		ServiceLoader<ISpecialUser> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);

		Iterator<ISpecialUser> iterator = serviceLoader.iterator();
		while (iterator.hasNext()) {
			ISpecialUser userFactory = iterator.next();
			System.out.println(userFactory.getSpecialUser());
		}

	}
}
