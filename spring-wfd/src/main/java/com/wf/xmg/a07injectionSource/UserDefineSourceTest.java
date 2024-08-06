package com.wf.xmg.a07injectionSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// spring中 依赖来源 用户自定义的 和手动添加到系统中的
public class UserDefineSourceTest {


	@Autowired
	@Qualifier("user")
	private User user;


	@Autowired
	@Qualifier("user1")
	private User user1;



	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.registerBean(UserDefineSourceTest.class);

		registerBean(applicationContext);// 这行手动注入的必须再刷新之前，因为在刷新的过程中会进行依赖的处理，如果依赖的数据来源于手动注入的，则会进行报错，找不到bean
		applicationContext.refresh();
		//	registerBean(applicationContext);
		UserDefineSourceTest demo = applicationContext.getBean(UserDefineSourceTest.class);

		System.out.println("user: " +demo.user);
		System.out.println("user1: " +demo.user1);

	}


	//  系统应用的bean
	@Bean
	public User user(){
		User user = new User();
		user.setAge(12);
		user.setName("小明");
		return user;
	}

	// 手动注册的bean
	public static void registerBean(AnnotationConfigApplicationContext context){

		context.registerBean("user1", User.class,()->{
			User user = new User();
			user.setAge(22);
			user.setName("xmg");
			return user;
		});
	}



}
