package com.wf.xmg.a05whatIsAutowiring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// 什么是autowiring模式 自动注入模式
public class AutoWiringTest {

	public static void main(String[] args) {

		/**
		 *  在xml时期，我们配置文件类与类之间进行关联时(依赖注入)，需要手动进行指定对应的属性与相关对象的关联
		 *  为了减少手动配置关联关系，spring提供了自动装配的功能，通过制定Autowire属性，可以将我们的属性关系进行自动依赖绑定。 teacher1 与teacher2的区别
		 *  需要注意的是,spring 并不支持基本类型的自动装配，只支持引用类型的装配
		 *
		 *  在进行xml使用autowiring时，spring会调用对应类属性的setter方法(可写方法)，一定是setter属性的方法，方法名也是唯一的.不能使用其他的方法名称
		 *
		 *
		 */
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/bean-injection-autowiring.xml");

		Teacher teacher = context.getBean("teacher", Teacher.class);
		Teacher teacher1 = context.getBean("teacher1", Teacher.class);

		System.out.println(teacher);
		System.out.println(teacher1);


	}
}
