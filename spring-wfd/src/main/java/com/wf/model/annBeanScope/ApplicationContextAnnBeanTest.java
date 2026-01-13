package com.wf.model.annBeanScope;

import com.wf.model.annBean.ParameterBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.UnsupportedEncodingException;

/**
 * @Desc : 注解 @Bean的处理大致逻辑
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
@ComponentScan(basePackages = {"com.wf.model.annBeanScope"})
public class ApplicationContextAnnBeanTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

		// ApplicationContext等上下文中存放了一个DefaultListableBeanFactory容器对象
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContextAnnBeanTest.class);


		ComponentScope componentScope = (ComponentScope) annotationConfigApplicationContext.getBean("componentScope");
		System.out.println(componentScope);

		ConfigurationScope configurationScope = (ConfigurationScope) annotationConfigApplicationContext.getBean("configurationScope");
		System.out.println(configurationScope); // 这个配置类变成了代理


		Cat componentScopeCat = (Cat) annotationConfigApplicationContext.getBean("catComponentScope");
		System.out.println(componentScopeCat.getCategory()); // 这个和下面的不是同一
		System.out.println(annotationConfigApplicationContext.getBean("cateS"));


        Cat catConfigurationScope = (Cat) annotationConfigApplicationContext.getBean("catConfigurationScope");
		System.out.println(catConfigurationScope.getCategory()); // 这个和下面的是同一个
		System.out.println(annotationConfigApplicationContext.getBean("cateC"));


		// 因为 @Configuration CGLIB 存在提升特性，所以多次获得都是一个
		/** 代理的关键作用：
		 * 拦截@Bean方法调用：将方法调用重定向到BeanFactory
		 * 维护单例语义：确保相同bean多次引用时返回同一实例
		 * 处理依赖关系：正确注入交叉依赖的bean
		 *
		 * FULL模式 和LIFE模式
		 * 代理配置类（FULL模式）的优点：
		 * 保证单例语义：最重要的原因
		 * 支持方法间调用：使配置更灵活
		 * 容器管理生命周期：Bean完全由Spring管理
		 * 支持AOP等高级特性：代理是基础
		 *
		 * 何时使用LITE模式：
		 * 配置类无方法间调用时
		 * 追求启动速度（避免CGLIB生成）
		 * 在@Bean方法中直接new对象，不依赖其他@Bean方法
		 * 配置类被频繁扫描（如Spring Boot自动配置）
		 *
		* */
    }






}
