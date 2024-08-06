package com.wf.xmg.a07injectionSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

// 内建非bean的依赖
public class ResolvableInjectionSourceTest {

	// 系统内建的bean
	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	// 手动自定义的依赖bean
	@Autowired
	private User user;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(ResolvableInjectionSourceTest.class);


		// 手动注册我们的非bean
		context.addBeanFactoryPostProcessor(beanFactory -> {
			// 注册 Resolvable Dependency
			User user = new User();
			user.setName("Hello,World");
			user.setAge(18);
			beanFactory.registerResolvableDependency(User.class, user);
		});  // 要在手动刷新之前进行设置哦


		context.refresh();

		ResolvableInjectionSourceTest demo = context.getBean(ResolvableInjectionSourceTest.class);

		System.out.println("beanFactory == applicationContext " + (demo.beanFactory == demo.applicationContext));
		System.out.println("beanFactory == applicationContext.getBeanFactory() " + (demo.beanFactory == demo.applicationContext.getAutowireCapableBeanFactory()));
		System.out.println("resourceLoader == applicationContext " + (demo.resourceLoader == demo.applicationContext));
		System.out.println("ApplicationEventPublisher == applicationContext " + (demo.applicationEventPublisher == demo.applicationContext));

		System.out.println("user " + demo.user);


	}
}
