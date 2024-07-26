package com.wf.xmg.a06injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

//05 标注在字段上的依赖注入
public class AnnotationFieldInjectionTest {


	@Autowired
	private User user;

	@Autowired
	private static User userStatic; // 静态的是否可以进行依赖注入呢？

	@Autowired
	private UserHolder userHolder;


	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationFieldInjectionTest.class);

		AnnotationFieldInjectionTest annotationFieldInjectionTest = applicationContext.getBean(AnnotationFieldInjectionTest.class);

		System.out.println("annotationFieldInjectionTest.user："+annotationFieldInjectionTest.user);
		System.out.println("annotationFieldInjectionTest.Static.user："+AnnotationFieldInjectionTest.userStatic);
		System.out.println("annotationFieldInjectionTest.userHolder："+annotationFieldInjectionTest.userHolder);
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
