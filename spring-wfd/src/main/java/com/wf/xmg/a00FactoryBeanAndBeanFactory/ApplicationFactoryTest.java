package com.wf.xmg.a00FactoryBeanAndBeanFactory;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;
// 01 BeanFactory  FactoryBean 与   ObjectFactory 有什么区别
public class ApplicationFactoryTest {

	/**
	 *  BeanFactory接口 是我们底层的容器，
	 *  FactoryBean接口 是一个bean，这个bean也用来创建对象，我们可以通过此接口来创建对象，而且创建的对象会被spring管理，但是会没有beanName,无法通过自己的beanName进行依赖查找，可以通过类型进行查找，能被依赖注入解析到
	 *  ObjectFactory对象工厂接口，我们可以将此注入到spring中，然后通过此对象来获取对象，
	 *  	但是注意通过此对象(ObjectFactory)获取到的对象不会被Spring管理，每次得到的时候都会走到对应的getObject获取对象
	 *  ObjectProvider接口继承自ObjectFactory接口，进行了功能扩展，可以安全的获取到对应的对象
	**/
	public static void main(String[] args) {

		DefaultListableBeanFactory  beanFactory = new DefaultListableBeanFactory();

		//  ClassPathBeanDefinitionScanner 定义一个类路径下的beanDefinition扫描器，将扫描的BeanDefinition，进行注册到BeanDefinitionRegistry
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);
		scanner.scan("com.wf.xmg.a00FactoryBeanAndBeanFactory"); // 扫描此包下面的
		beanFactory.addBeanPostProcessor(beanFactory.getBean(AutowiredAnnotationBeanPostProcessor.class));


		// 针对于DefaultListableBeanFactory 容器来讲，只有在getBean的时候才会进行实例化
		Student student = beanFactory.getBean("student",Student.class);
		System.out.println("sutdent: "+student);


		// 注意这里获取的name 为什么是teacherFactoryBean ,而且我们的Teacher进行了实现Aware接口进行注入对应的beanName用于查看是否存在bean名称
		Teacher teacher = beanFactory.getBean("teacherFactoryBean",Teacher.class);
		System.out.println("teacher: "+teacher);
		Teacher teacher2 = beanFactory.getBean("teacherFactoryBean",Teacher.class); // 之后进行一次
		System.out.println("teacher: "+teacher2);
		Teacher teacherOrg = beanFactory.getBean(Teacher.class);
		System.out.println("teacher可以直接通过类型获取吗?: "+teacher);
		/*Teacher teacherByName = applicationContext.getBean("null",Teacher.class); 上面打印了beanName.为什么还不能获取到呢？
		System.out.println("teacher可以直接通过名称类型获取吗?: "+teacherByName);*/
		TeacherFactoryBean teacherFactoryBean = beanFactory.getBean("&teacherFactoryBean",TeacherFactoryBean.class);
		System.out.println("teacherFactoryBean: "+teacherFactoryBean);




		 // 为什么直接获取不到呢？而且还注入不了，因为ObjectFactory的对象并不是被spring管理，但是上层的SchoolObjectFactory被管理了
		/*School school = applicationContext.getBean(School.class);
		System.out.println("school:"+school);*/
		// 可以当做原型使用
		SchoolObjectFactory schoolObjectFactory = beanFactory.getBean("schoolObjectFactory",SchoolObjectFactory.class);
		School object = schoolObjectFactory.getObject();

		System.out.println("school: "+object);
		School object1 = schoolObjectFactory.getObject();
		System.out.println("school: "+object1);


		ContainAutowireDto bean = beanFactory.getBean(ContainAutowireDto.class);
		System.out.println(bean);


	}
}
