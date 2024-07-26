package com.wf.xmg.a06injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// 07 集合类型的依赖注入
public class AnnotationCollectionInjectionTest {

	@Autowired
	private List<User> users;

	@Autowired
	private Map<String,User> usersMap;

	@Autowired
	private Optional<User> userOptional; //

	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationCollectionInjectionTest.class);

		AnnotationCollectionInjectionTest annotationFieldInjectionTest = applicationContext.getBean(AnnotationCollectionInjectionTest.class);

		System.out.println("annotationFieldInjectionTest.List.user："+ annotationFieldInjectionTest.users);
		System.out.println("annotationFieldInjectionTest.map.user："+annotationFieldInjectionTest.usersMap);

		System.out.println("annotationFieldInjectionTest.Optional.user："+annotationFieldInjectionTest.userOptional.get());
	}


	@Bean
	public User user() {
		User user = new User();
		user.setName("张三");
		user.setAge(18);
		return user;
	}

	@Bean
	@Primary
	public User user1() {
		User user = new User();
		user.setName("李四");
		user.setAge(19);
		return user;
	}

	@Bean
	public User user2() {
		User user = new User();
		user.setName("王五");
		user.setAge(20);
		return user;
	}



}
