package com.wf.model.annBeanFactory;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
/**
* @Desc : 简单的容器BeanFactory的使用
* @Author : Mr.WangF
**/
public class AnnBeanFactoryTest {


	public static void main(String[] args) {
		int i=0;
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 在我们实例化我们的AnnotatedBeanDefinitionReader的时候，其实已经帮我内置了注解所需要的beanDefinition相关，我们可以直接得到对象即可
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

		// 先手动激活相关的注解的后置处理器 ,如果不进行激活，无法进行使用，因为后置处理器没有被实例化
		//beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
		AutowiredAnnotationBeanPostProcessor bean = beanFactory.getBean(AutowiredAnnotationBeanPostProcessor.class);
		beanFactory.addBeanPostProcessor(bean);
		// 注册是注册为BeanDefinition
		reader.register(Cat.class, Person.class);

		// 在容器中，获得bean的时候才会进行创建bean
		Cat cat = beanFactory.getBean(Cat.class);
		Person person = beanFactory.getBean(Person.class);
		System.out.println(cat);
		System.out.println(person);

	}
}
