package com.wf.xmg.a06injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// 04 注解下的构造器依赖注入
public class AnnotationConstructorTest {

	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationConstructorTest.class);


		User user = applicationContext.getBean(User.class);
		System.out.println("获取到系统中的user:"+user);


		UserHolder userHolder = applicationContext.getBean("setterUserHolder", UserHolder.class);
		System.out.println("获取到系统中的userHolder:"+userHolder);

		System.out.println("获取到系统中的user与 userHolder中的user是否相等:"+(user == userHolder.getUser()));


		UserHolder userHolder1 = applicationContext.getBean("setterUserHolder1", UserHolder.class);
		System.out.println("获取到系统中的通过方法引用调用的userHolder:"+userHolder1);

		System.out.println("系统中的user与系统中的通过方法引用调用userHolder1的user是否相等:"+(user == userHolder1.getUser()));

	}



	@Bean
	public User user() {
		User user = new User();
		user.setName("张三");
		user.setAge(18);
		return user;
	}

	@Bean
	public UserHolder setterUserHolder(User user) {
		// 构造器注入
		UserHolder userHolder = new UserHolder(user);
		return userHolder;
	}

	// 注意通过方法调用的会产生新的对象
	@Bean
	public UserHolder setterUserHolder1() {
		// 构造器注入
		UserHolder userHolder = new UserHolder(user());
		return userHolder;
	}
}
