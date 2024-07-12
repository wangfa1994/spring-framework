package com.wf.xmg.a03singletonBean;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// 03 单例的bean 是如何进行初始化的
public class SingletonBeanInitializationTest {

	/**c
	 * 初始化过程中
	 * 会先执行java的注解相关 @PostConstruct
	 * 然后执行spring的内置接口相关 InitializingBean
	 * 最后执行我们自定义的方法 @Bean中指定的initMethod
	 *
	 * 销毁过程中
	 * 会先执行java的注解相关 @PreDestroy
	 * 然后执行spring的内置接口相关 DisposableBean
	 * 最后执行我们自定义的方法 @Bean中指定的destroyMethod
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
