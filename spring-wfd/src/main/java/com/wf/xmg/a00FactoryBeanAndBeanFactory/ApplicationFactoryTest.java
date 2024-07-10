package com.wf.xmg.a00FactoryBeanAndBeanFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
//  BeanFactory  FactoryBean 与   ObjectFactory 有什么区别
public class ApplicationFactoryTest {

	/**
	 *  BeanFactory接口 是我们底层的容器，
	 *  FactoryBean接口 是一个bean，这个bean也用来创建对象，我们可以通过此接口来创建对象，而且创建的对象会被spring管理，
	 *  ObjectFactory对象工厂接口，我们可以将此注入到spring中，然后通过此对象来获取对象，
	 *  	但是注意通过此对象(ObjectFactory)获取到的对象不会被Spring管理，每次得到的时候都会走到对应的getObject获取对象
	 *  ObjectProvider接口继承自ObjectFactory接口，进行了功能扩展
	**/
	public static void main(String[] args) {


		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class);

		Student student = applicationContext.getBean("student",Student.class);
		System.out.println("sutdent: "+student);
		// 注意这里获取的name 为什么是teacherFactoryBean
		Teacher teacher = applicationContext.getBean("teacherFactoryBean",Teacher.class);
		System.out.println("teacher: "+teacher);
		TeacherFactoryBean teacherFactoryBean = applicationContext.getBean("&teacherFactoryBean",TeacherFactoryBean.class);

		System.out.println("teacherFactoryBean: "+teacherFactoryBean);

		 // 为什么直接获取不到呢？而且还注入不了，因为ObjectFactory的对象并不是被spring管理，但是上层的SchoolObjectFactory被管理了
		School school = applicationContext.getBean(School.class);
		System.out.println("school:"+school);
		// 可以当做原型使用
		SchoolObjectFactory schoolObjectFactory = applicationContext.getBean("schoolObjectFactory",SchoolObjectFactory.class);
		School object = schoolObjectFactory.getObject();

		System.out.println("school: "+object);
		School object1 = schoolObjectFactory.getObject();
		System.out.println("school: "+object1);



	}
}
