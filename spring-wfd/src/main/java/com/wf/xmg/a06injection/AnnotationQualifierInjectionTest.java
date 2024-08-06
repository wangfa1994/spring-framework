package com.wf.xmg.a06injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collection;

// @Qualifier 指定使用那个bean进行依赖注入 与 进行逻辑分组
public class AnnotationQualifierInjectionTest {

	@Autowired
	private User user; //  user1

	@Autowired
	@Qualifier("user1") // 指定 Bean 名称或 ID
	private User namedUser;


	@Autowired
	private Collection<User> allUsers; // 2 Beans = user1 + user2 +user3 + user4  是否标注Qualifier都会被进行收集

	@Autowired
	@Qualifier // 使用Qualifier的被进行了逻辑分组
	private Collection<User> qualifiedUsers; //  2 Beans = user3 + user4 标注了Qualifier的两个被收集




	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationQualifierInjectionTest.class);

		AnnotationQualifierInjectionTest test = applicationContext.getBean(AnnotationQualifierInjectionTest.class);

		System.out.println("user："+ test.user);

		System.out.println("user："+ test.user);
		System.out.println("namedUser："+ test.user);
		System.out.println("allUsers："+ test.allUsers);
		System.out.println("qualifiedUsers："+ test.qualifiedUsers);

	}


	@Bean
	@Primary
	public User user1() {
		User user = new User();
		user.setName("张三");
		user.setAge(18);
		return user;
	}

	@Bean
	public User user2() {
		User user = new User();
		user.setName("李四");
		user.setAge(19);
		return user;
	}

	@Bean
	@Qualifier
	public User user3() {
		User user = new User();
		user.setName("王五");
		user.setAge(20);
		return user;
	}

	@Bean
	@Qualifier
	public User user4() {
		User user = new User();
		user.setName("赵六");
		user.setAge(20);
		return user;
	}

}
