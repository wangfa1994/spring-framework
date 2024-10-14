package com.wf.xmg.a03singletonBean;

import com.wf.xmg.a03singletonBean.domain.User;
import com.wf.xmg.a03singletonBean.domain.UserFactory;
import com.wf.xmg.a03singletonBean.domain.UserFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 02 bean进行实例化的方式有哪些
public class SingletonBeanInstantiationTest {

	/**
	* 我们可以通过注册器将我们的实例bean直接注册到容器中，我们时候还要其他方法进行实例化bean呢？
	 * 对象的实例化其实是通过构造器进行实例化的，但是我们有哪些方式可以通过构造器进行实例化呢？
	 *
	 * 1.构造器实例化
	 * 2.静态方法实例化 Bean
	 * 3.实例（Bean）方法实例化 Bean
	 * 4.FactoryBean实例化 Bean
	 * 5.通过注解@Component，@Bean等注解实例化 Bean
	 *
	 *
	 * 针对FactoryBean实例化 spring内置了一个特殊的ServiceLoaderFactoryBean,这个特殊的bean为了适配spi机制
	 *
	 *
	 * 注意区分 bean的实例化方式 与 依赖注入的方式
	 *
	 *
	 *
	 */
	public static void main(String[] args) {

		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/a03/bean-instantiation-context.xml");
		User user = beanFactory.getBean("user-by-static-method", User.class);
		User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
		User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
		System.out.println(user);
		System.out.println(userByInstanceMethod);
		System.out.println(userByFactoryBean);

		System.out.println(user == userByInstanceMethod);
		System.out.println(user == userByFactoryBean);

		System.out.println("=====================注解==============================");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(SingletonBeanInstantiationTest.class);
		applicationContext.refresh();

		User contextUser = applicationContext.getBean("userByStatic", User.class);
		User contextUserByInstanceMethod = applicationContext.getBean("userByInstance", User.class);
		User contextUserByFactoryBean = applicationContext.getBean("userByFactoryBean", User.class);
		System.out.println(contextUser);
		System.out.println(contextUserByInstanceMethod);
		System.out.println(contextUserByFactoryBean);

	}


	@Bean
	public User user() {
		User user = new User(1L, "xmg");
		return user;
	}


	@Bean
	public User userByStatic() {
		// 静态方法
		return User.createUser();
	}

	

	@Bean
	public UserFactory userFactory() {
		UserFactory userFactory = new UserFactory();
		return userFactory;
	}
	
	@Bean
	public User userByInstance(UserFactory userFactory) {
		// 实例方法
		return userFactory.createUser();
	}

	
	@Bean
	public UserFactoryBean userByFactoryBean() {
		UserFactoryBean userFactoryBean = new UserFactoryBean();
		return userFactoryBean;
	}



}
