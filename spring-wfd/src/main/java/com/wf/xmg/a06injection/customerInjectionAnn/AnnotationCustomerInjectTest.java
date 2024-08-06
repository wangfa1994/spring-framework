package com.wf.xmg.a06injection.customerInjectionAnn;

import com.wf.xmg.a06injection.User;
import com.wf.xmg.a06injection.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// 08 自定义依赖注入注解
public class AnnotationCustomerInjectTest {


	@InjectUser
	private User user;

	@InjectUser
	private UserHolder userHolder;

	@Autowired
	private User user2;

	// 自己设置自己处理自己注解的BeanPostProcessor
	@Bean
	public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
		AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
		beanPostProcessor.setAutowiredAnnotationType(InjectUser.class); // 为什么我加入一个，系统的不会受到影响呢?
		// beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
		return beanPostProcessor;
	} // 此方法为什么加上static 就可以进行依赖注入呢？

	public static void main(String[] args) {

		ApplicationContext applicationContext =new AnnotationConfigApplicationContext(AnnotationCustomerInjectTest.class);


		AnnotationCustomerInjectTest annotationFieldInjectionTest = applicationContext.getBean(AnnotationCustomerInjectTest.class);

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
