package com.wf.xmg.a06injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// 03.注解下的setter方法的依赖注入
public class AnnotationSetterTest {


	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationSetterTest.class);


		User user = applicationContext.getBean(User.class);
		System.out.println("获取到系统中的user:"+user);


		UserHolder userHolder = applicationContext.getBean(UserHolder.class);
		System.out.println("获取到系统中的userHolder:"+userHolder);

		System.out.println("获取到系统中的user与 userHolder中的user是否相等:"+(user == userHolder.getUser()));


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
		UserHolder userHolder = new UserHolder();
		userHolder.setUser(user); // 是否可以利用方法进行直接引用呢？ setterUserHolder.setUser(user());
		return userHolder;
	}

}
