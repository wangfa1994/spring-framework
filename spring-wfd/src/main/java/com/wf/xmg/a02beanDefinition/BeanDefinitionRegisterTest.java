package com.wf.xmg.a02beanDefinition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;

// 注册 beanDefinition
public class BeanDefinitionRegisterTest {
	/**
	 * BeanDefinitionRegistry 体系，注册beanDefinition，通过此体系结构可以将我们创建的beanDefinition进行注册到不同的环境中去
	 *  1.SimpleBeanDefinitionRegistry:仅提供注册中心功能，没有内置工厂功能。
	 *  2.DefaultListableBeanFactory:  在工厂中进行注册
	 *  3.GenericApplicationContext:  在应用上下文中进行注册
	 *
	 *
	 * BeanDefinition的注册主要通过两种方式:首先我们需要明白的是,我们的BeanDefinition注册到哪里? 不同的实现有不同的注册地方
	 *  1.通过相关实现类进行注册。不同的实现类有不同的注册环境，比如GenericApplicationContext是注入到我们的上下文环境中
	 *  2.通过 BeanDefinitionReaderUtils工具类进行注册，此时需要告诉我们的类要注册到哪里去，也是需要传递对应的BeanDefinitionRegistry实现
	 *
	 */

	public static void main(String[] args) {
		// 创建一个应用上下文
		GenericApplicationContext applicationContext = new GenericApplicationContext();

		//手动注入beanDefinition 通过Application上下文注册
		applicationContext.registerBeanDefinition("student",createBeanDefinition().getBeanDefinition());
		applicationContext.registerBeanDefinition("student01",createBeanDefinitionR());

		// 通过BeanDefinitionReaderUtils工具类注册
		String beanName = BeanDefinitionReaderUtils.registerWithGeneratedName(createBeanDefinitionG(), applicationContext);
		System.out.println("系统帮我们生成的beanName为："+beanName); // com.wf.xmg.a02beanDefinition.Student#0

		/*BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(genericBeanDefinition,"student02");
		BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, applicationContext);
		Student student02 = applicationContext.getBean("student02", Student.class); */


		BeanDefinition student1 = applicationContext.getBeanDefinition("student");
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		StringBuilder beanDefinitionName =new StringBuilder();
		for (String item : beanDefinitionNames) {
			beanDefinitionName.append(item).append(",");
		}

		System.out.println("beanDefinitionNames:"+beanDefinitionNames);
		int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
		System.out.println("一共加载了【"+beanDefinitionCount+"】个beanDefinition");
		System.out.println("一共加载了【"+beanDefinitionName.toString()+"】个beanDefinitionName");
		System.out.println("beanDefinition:student1:"+student1);

		/*// 刷新容器 将我们的BeanDefinition变成实例对象
		//applicationContext.refresh();

		// 进行获得对应的bean
		Student student = applicationContext.getBean("student", Student.class);
		Student student01 = applicationContext.getBean("student01", Student.class);
		Student student02 = applicationContext.getBean(beanName, Student.class);


		System.out.println("student:"+student);
		System.out.println("student01:"+student01);
		System.out.println("student02:"+student02);*/
	}

	static BeanDefinitionBuilder createBeanDefinition(){
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Student.class);
		beanDefinitionBuilder.setLazyInit(false);
		beanDefinitionBuilder.addPropertyValue("name", "xmg")
				.addPropertyValue("age", 18);
		return beanDefinitionBuilder;
	}

	static BeanDefinition createBeanDefinitionR(){
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.setBeanClass(Student.class);
		rootBeanDefinition.setLazyInit(false);
		MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
		mutablePropertyValues.addPropertyValue("name", "xmg01");
		mutablePropertyValues.addPropertyValue("age", 19);
		rootBeanDefinition.setPropertyValues(mutablePropertyValues);
		return rootBeanDefinition;
	}

	static GenericBeanDefinition createBeanDefinitionG(){
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(Student.class);
		MutablePropertyValues mutablePropertyValuesG = new MutablePropertyValues();
		mutablePropertyValuesG.add("name","xmg02")
				.add("age",20);
		genericBeanDefinition.setPropertyValues(mutablePropertyValuesG);
		return genericBeanDefinition;
	}

}
