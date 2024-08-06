package com.wf.xmg.a06injection;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

// 延迟注入与实时注入
public class AnnotationFieldLazyInjectionTest {

	// 实时注入
	@Autowired
	private User user;

	// 实时注入
	@Autowired
	private UserHolder userHolder;

	// 延迟注入 使用lazy 创建的是代理对象哦,查看Lazy的注释
	@Autowired
	@Lazy
	private User userLazy;

	// 延迟注入 使用lazy 创建的是代理对象哦
	@Autowired
	@Lazy
	private UserHolder userHolderLazy;

	// 延迟依赖注入
	@Autowired
	private ObjectProvider<User> userObjectProvider;

	// 延迟依赖注入，此种方式会直接走依赖解决方法
	@Autowired
	private ObjectFactory<UserHolder> userHolderObjectFactory;


	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationFieldLazyInjectionTest.class);

		AnnotationFieldLazyInjectionTest annotationFieldLazyInjectionTest = applicationContext.getBean(AnnotationFieldLazyInjectionTest.class);

		System.out.println("user："+annotationFieldLazyInjectionTest.user);
		System.out.println("userHolder："+annotationFieldLazyInjectionTest.userHolder);

		System.out.println("user："+annotationFieldLazyInjectionTest.userLazy);
		System.out.println("userHolder："+annotationFieldLazyInjectionTest.userHolderLazy);

		System.out.println("user == userLazy  ："+(annotationFieldLazyInjectionTest.user == annotationFieldLazyInjectionTest.userLazy));
		System.out.println("userHolder == userHolderLazy："+(annotationFieldLazyInjectionTest.userHolder == annotationFieldLazyInjectionTest.userHolderLazy));



		System.out.println("userObjectProvider："+annotationFieldLazyInjectionTest.userObjectProvider.getIfAvailable());
		System.out.println("userHolderObjectFactory："+annotationFieldLazyInjectionTest.userHolderObjectFactory.getObject());

		System.out.println("user == userObjectProvider  ："+(annotationFieldLazyInjectionTest.user == annotationFieldLazyInjectionTest.userObjectProvider.getIfAvailable()));
		System.out.println("userHolder == userHolderObjectFactory："+(annotationFieldLazyInjectionTest.userHolder == annotationFieldLazyInjectionTest.userHolderObjectFactory.getObject()));

	}


	@Bean
	public User user() {
		User user = new User();
		user.setName("张三");
		user.setAge(((int) (Math.random() * 100)));
		return user;
	}

	@Bean
	public UserHolder userHolder() {
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user());
		return userHolder;

	}
}
