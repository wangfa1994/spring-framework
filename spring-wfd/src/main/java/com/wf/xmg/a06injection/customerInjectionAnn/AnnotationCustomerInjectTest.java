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
	public static  AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
		AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
		beanPostProcessor.setAutowiredAnnotationType(InjectUser.class); // 为什么我加入一个，系统的不会受到影响呢?
		// beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
		return beanPostProcessor;
	} // 此方法为什么加上static 就可以进行依赖注入呢？
	/**
	 *  首先会解析我们所有的BeanDefinition,然后在上下文处理的时候会进行BeanPostProcessor的初始化，
	 *  在进行初始化的时候，如果我们使用了实例方法，则会先进行对应实例的初始化，然后再调用对应的实例方法。此时我们的AnnotationCustomerInjectTest就会被提前初始化了，导致属性无法处理。
	 *  如果我们使用了对应的静态方法，此时不会先进行对应类的初始化，直接进行静态方法的调用，实例化处理完我们的BeanPostProcessor之后，在进行对应处理的时候就可以使用上我们的后置处理器了。
	 *  所以如果不加static，导致了我们的AnnotationCustomerInjectTest被提前初始化了。属性也就放不进去了。
	 *
	 *
	 */


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
