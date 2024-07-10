package com.wf.xmg.a01applicationAndFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;

// 容器中的bean来自哪里
@ComponentScan(basePackages = {"com.wf.xmg.a01applicationAndFactory"})
public class WhereBeanFromTest {

	/**
	 * ApplicationContext是一个应用上下文，内部组合了一个BeanFactory对象,这个内部的beanFactory才是真正的底层容器,有一种代理的模式的感觉.
	 * 应用上下文为我们提供了更多的功能,环境，资源，应用信息等，而BeanFactory是一个底层容器，只有一些从容器中获取对象的方法
	 *
	 * 容器中的bean来源主要包括 用户自定义bean, 系统内建bean, 非bean。
	 * 	1. 用户自定义的bean，就是我们使用@Bean,@Component等进行标注的bean
	 * 	2. 系统内建的bean，就是我们系统中Spring给我们内置的一些可供我们获取环境相关bean,可以通过getBean得到，可以进行依赖注入
	 * 	3. 非bean，就是我们通过getBean获取不到对应的值，但是可以被依赖注入到系统中的bean，系统上的bean
	 *
	 * 在prepareBeanFactory()中进行处理相关逻辑
	 *  spring容器中的数据来源主要包括三个部分：自定义bean，spring内置bean，spring的非bean
	 *
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

		//AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationAndFactoryConfiguration.class);

		// 为什么可以用BeanFactory进行接收呢？
		BeanFactory annotationConfigApplicationContext = new AnnotationConfigApplicationContext(WhereBeanFromTest.class);

		// 在person中注入了 application 与 BeanFactory ,比较是否相同
		Person person = (Person) annotationConfigApplicationContext.getBean("person");

		BeanFactory beanFactory = person.getBeanFactory();
		ApplicationContext applicationContext = person.getApplicationContext();

		System.out.println("person中注入的BeanFactory与ApplicationContext是否相等："+(beanFactory == applicationContext)); //false
		System.out.println("person中注入的BeanFactory与外层的BeanFactory是否同等： "+(beanFactory == annotationConfigApplicationContext));  //false
		System.out.println("person中注入的ApplicationContext与外层的BeanFactory是否同等： "+(applicationContext == annotationConfigApplicationContext)); // true

		// 问题二： 我们通过Autowire可以进行注入我们的BeanFactory,ApplicationContext 于是我们是否可以从容器中得到我们的BeanFactory呢？
		// 这两个都无法得到，但是为什么可以进行依赖注入呢？这种就属于我们所说的非bean 的注入
		/*ApplicationContext applicationContextBean = annotationConfigApplicationContext.getBean(ApplicationContext.class);
		System.out.println("从容器中拿出我们的上下文相关bean:"+applicationContextBean);
		BeanFactory bean = annotationConfigApplicationContext.getBean(BeanFactory.class);
		System.out.println(bean);*/


		// 问题三：我们没有注入我们的Environment,是否可以获取呢？
		// 这两个都可以得到，但是我们没有进行注入，这个是属于系统给我们进行注入的。
		Environment environment0 = person.getEnvironment();
		System.out.println("从person中拿出我们的环境相关bean:"+environment0);
		Environment environment = annotationConfigApplicationContext.getBean(Environment.class);
		System.out.println("从容器中拿出我们的环境相关bean:"+environment);


		// 针对Application 与 FactoryBean  是不是有一种代理模式的味道
	}


}
