package com.wf.xmg.a03singletonBean;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// 03 单例的bean 是如何进行初始化的
public class SingletonBeanInitializationTest {

	/**c
	 * 初始化过程中
	 * 会先执行java的注解相关 @PostConstruct 。这个是通过BeanPostProcessor的前置处理器进行处理的，
	 * 然后执行spring的内置接口相关 InitializingBean 这个是通过 invokeInitMethods 方法进行执行的
	 * 最后执行我们自定义的方法 @Bean中指定的initMethod  这个是通过 invokeInitMethods 方法进行执行的
	 *
	 * 销毁过程中
	 * 会先执行java的注解相关 @PreDestroy  这个是通过BeanPostProcessor的前置处理器进行处理的，
	 * 然后执行spring的内置接口相关 DisposableBean  这个是通过接口调用
	 * 最后执行我们自定义的方法 @Bean中指定的destroyMethod 这个自定义的处理
	 *
	 *
	 *
	 * bean的初始化包括在beanFactory容器中的初始化流程 和 在applicationContext上下文中的初始化流程
	 *
	 *
	 * org.springframework.beans.factory.support.DisposableBeanAdapter#destroy()
	 * 销毁的时候， 先销毁注解，在销毁接口实现，最后再销毁自定义方法
	 *
	 * 销毁的逻辑会被封装到 DisposableBeanAdapter 类中进行处理
	 *
	 *
	 */


	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingletonBeanInitializationTest.class);
		TeacherInitialization teacherInitialization = applicationContext.getBean(TeacherInitialization.class);
		System.out.println("打印:"+teacherInitialization);
		System.out.println("Spring 应用上下文准备关闭...");
		// 关闭 Spring 应用上下文
		applicationContext.close();
		System.out.println("Spring 应用上下文已关闭...");
	}


	@Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
	public TeacherInitialization teacherInitialization() {
		return new TeacherInitialization();
	}
}
