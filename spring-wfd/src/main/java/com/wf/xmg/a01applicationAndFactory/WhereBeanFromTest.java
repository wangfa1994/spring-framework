package com.wf.xmg.a01applicationAndFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;

//03 容器中的bean来自哪里。依赖注入的bean有哪些

//@Import(BeanFromDto.class)
@Import(BeanFromCusDto.class)
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
	 *  spring容器中的数据来源主要包括三个部分：自定义bean，spring内置bean，spring的非bean(游离的bean)
	 *
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

		// 启用 类头上的 @Import(BeanFromDto.class)
		//whereBeanFrom();


		//启用 类头上的  @Import(BeanFromCusDto.class)
		customerResolvableDependency();


	}

	private static void customerResolvableDependency() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(WhereBeanFromTest.class);
		// 手动注册我们的非bean
		context.addBeanFactoryPostProcessor(realBeanFactory -> {
			// 注册 Resolvable Dependency 这个bean会被处理成非bean进行依赖注入
			Person personDependency = new Person();
			personDependency.setName("Hello,World");
			realBeanFactory.registerResolvableDependency(Person.class, personDependency);
		});

		/*ConfigurableListableBeanFactory autowireCapableBeanFactory = (ConfigurableListableBeanFactory)context.getAutowireCapableBeanFactory();
		Person personDependency = new Person();
		personDependency.setName("Hello,World");
		autowireCapableBeanFactory.registerResolvableDependency(Person.class, personDependency);*/

		context.refresh();// 必须在刷新之前进行设置，这样的话，刷新的时候在处理依赖注入的时候才可以使用




		BeanFromCusDto bean = context.getBean(BeanFromCusDto.class);
		System.out.println(bean.getPerson());
	}


	private static void whereBeanFrom() {
		//AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(WhereBeanFromTest.class);

		// 为什么可以用BeanFactory进行接收呢？
		BeanFactory annotationConfigApplicationContext = new AnnotationConfigApplicationContext(WhereBeanFromTest.class);

		// 在BeanFromDto中注入了 application 与 BeanFactory ,比较是否相同
		BeanFromDto person = annotationConfigApplicationContext.getBean(BeanFromDto.class);

		BeanFactory beanFactory = person.getBeanFactory();//
		ApplicationContext applicationContext = person.getApplicationContext();


		BeanFactory applicationContextToBeanFactory = (BeanFactory)applicationContext; // 进行强转，是因为ApplicationContext也继承了ListableBeanFactory等BeanFactory
		System.out.println("person中注入的BeanFactory与ApplicationContext是否相等："+(beanFactory == applicationContextToBeanFactory));
		//false  这里当我们注入beanFactory的时候,注入的是我们上下文中的容器BeanFactory,而另外一个是上下文，也是beanFactory


		System.out.println("person中注入的BeanFactory与外层的BeanFactory是否同等： "+(beanFactory == annotationConfigApplicationContext));
		//false 这里理由同上，这里为什么没有进行转换呢？是因为我们直接用的及时BeanFactory进行接收的

		System.out.println("person中注入的ApplicationContext与外层的BeanFactory是否同等： "+(applicationContext == annotationConfigApplicationContext));
		// true  这里表明我们注入的ApplicationContext其实就是我们的上下文

		BeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
		System.out.println("person中注入的ApplicationContext中得到的beanFactory与外层的BeanFactory是否同等： "+(beanFactory == autowireCapableBeanFactory));
		// true  当我们把他当做ApplicationContext看待的时候，我们可以获取到我们的上下文中的底层工厂，其实这个工厂，才是我们注入的工厂


		//我们的上下文中的容器中管理这我们的bean，我们在依赖查找的时候就是去这个容器中进行获取的。但是我们的依赖注入呢？

		// 问题二： 我们通过Autowire可以进行注入我们的BeanFactory,ApplicationContext 于是我们是否可以从容器中得到我们的BeanFactory呢？
		// 这两个都无法得到，但是为什么可以进行依赖注入呢？这种就属于我们所说的非bean 的注入 (说明我们的依赖注入的来源不仅仅是我们的容器管理的bean)
		/*ApplicationContext applicationContextBean = annotationConfigApplicationContext.getBean(ApplicationContext.class);
		System.out.println("从容器中拿出我们的上下文相关bean:"+applicationContextBean);
		BeanFactory bean = annotationConfigApplicationContext.getBean(BeanFactory.class);
		System.out.println(bean);*/


		// 问题三：我们没有注入我们的Environment,是否可以获取呢？
		// 这两个都可以得到，但是我们没有进行注入，这个是属于上下文给我们进行注入的。(上下文内置了一些对象给我们放入到了我们的容器中，所以我们可以在getBean的时候帮我们获取到)
		Environment environment0 = person.getEnvironment();
		System.out.println("从person中拿出我们的环境相关bean:"+environment0);
		Environment environment = annotationConfigApplicationContext.getBean(Environment.class);
		System.out.println("从容器中拿出我们的环境相关bean:"+environment);
		System.out.println("依赖注入的和从容器中获取到的是否一致："+(environment == environment0));


		// 针对Application 与 FactoryBean  是不是有一种代理模式的味道

		// 上下文中的容器中的bean可能来源于两个部分：一个是我们自定义的业务bean，而另外一种就是上下文帮我们放置容器中的内建bean。
		// 在解析依赖来源的时候，我们的上下文不知会从容器中进行获取依赖来源，还会从非bean(游离在容器之外的bean)进行帮我们依赖。
		// 所以。游离在BeanFactory容器之外的实例，都有哪些？ DefaultListableBeanFactory.resolvableDependencies中存储

		// 打开 customerResolvableDependency方法
	}


}
