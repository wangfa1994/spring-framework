package com.wf.xmg.a06injection.customerInjectionAnn;

import com.wf.xmg.a06injection.User;
import com.wf.xmg.a06injection.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationCustomerInject2Test {

	@InjectUser
	private User user;

	@InjectUser
	private UserHolder userHolder;

	@Autowired
	private User user2;



	public static void main(String[] args) {

		// 这种情况下，直接提出去了相关类，所以不用关系是否可以提前初始化
		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationCustomerInject2Test.class,InjectUserAutoWiredAnnotationBeanPostProcessor.class);


		AnnotationCustomerInject2Test annotationFieldInjectionTest = applicationContext.getBean(AnnotationCustomerInject2Test.class);

		System.out.println("annotationFieldInjectionTest.InjectUser.user："+annotationFieldInjectionTest.user);
		System.out.println("annotationFieldInjectionTest.InjectUser.userHolder："+annotationFieldInjectionTest.userHolder);
		System.out.println("annotationFieldInjectionTest.Autowired.userHolder："+annotationFieldInjectionTest.user2);

		System.out.println("通过自定义注解的依赖注入和通过Autowire的依赖注入是否相同：" + (annotationFieldInjectionTest.user == annotationFieldInjectionTest.user2));
	}



	@Bean
	public User user() {
		User user = new User();
		user.setName("张三");
		user.setAge(18);
		return user;
	}

	@Bean
	public UserHolder userHolder() {
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user());
		return userHolder;

	}

}
