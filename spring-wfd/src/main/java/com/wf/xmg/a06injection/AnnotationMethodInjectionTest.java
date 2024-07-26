package com.wf.xmg.a06injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

// 06. 标注在方法上面的依赖注入
public class AnnotationMethodInjectionTest {


	private UserHolder userHolder;

	private UserHolder userHolder2;

	@Autowired
	public void init1(UserHolder userHolder) {
		this.userHolder = userHolder;
	}

	@Resource
	public void init2(UserHolder userHolder2) {
		this.userHolder2 = userHolder2;
	}

	@Bean
	public UserHolder userHolder(User user) {
		return new UserHolder(user);
	}


	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationMethodInjectionTest.class);

		AnnotationMethodInjectionTest annotationFieldInjectionTest = applicationContext.getBean(AnnotationMethodInjectionTest.class);

		System.out.println("通过方法进行标注对应的Autowired注解：userHolder="+annotationFieldInjectionTest.userHolder);
		System.out.println("通过方法进行标注对应的Autowired注解：userHolder2="+annotationFieldInjectionTest.userHolder2);
		System.out.println("userHolder == userHolder2是否相等:"+(annotationFieldInjectionTest.userHolder==annotationFieldInjectionTest.userHolder2));
	}

	@Bean
	public User user() {
		User user = new User();
		user.setName("wf");
		user.setAge(18);
		return user;
	}

	/*@Bean
	public UserHolder userHolder2(User user) {
		return new UserHolder(user);
	}*/
}
