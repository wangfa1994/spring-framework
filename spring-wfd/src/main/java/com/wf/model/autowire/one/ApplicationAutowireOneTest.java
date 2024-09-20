package com.wf.model.autowire.one;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Desc : @Autowire注解配置的分析，单个Cat
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
public class ApplicationAutowireOneTest {

    public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AutoWireOneConfigurations.class);
		APerson person = (APerson) annotationConfigApplicationContext.getBean("APerson");
		System.out.println(person);
		Cat cat1 = person.getCat();
		System.out.println("person的cat"+cat1);
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
		System.out.println("容器中的cat"+cat);

		/*Person person1 = (Person) annotationConfigApplicationContext.getBean("person");
		System.out.println("容器中的person"+person1);*/
    }

/**
 * ConfigurationClassPostProcessor 用于处理配置文件
 * AutowiredAnnotationBeanPostProcessor 用于处理@Autowire，@Value自动装配
 * CommonAnnotationBeanPostProcessor 用于处理 @Resource注解 @PostConstruct，@PreDestroy 支持JSR250
 * @AutoWire注解解析类 AutowiredAnnotationBeanPostProcessor
 *
 * // 内置的BeanDefinition是如何添加进去的？
 *
 *
 *
 * // 内置的后置处理器在哪里加上的？
 * 在prepareBeanFactory准备阶段放入了
 * 	  ApplicationContextAwareProcessor 与 ApplicationListenerDetector
 *
 * 在invokeBeanFactoryPostProcessors处理时机时
 * org.springframework.context.annotation.internalConfigurationAnnotationProcessor--ConfigurationClassPostProcessor--属于BeanDefinitionRegistryPostProcessor-属于BeanFactoryPostProcessor
 * --ConfigurationClassPostProcessor 实现属于BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry方法，用来解析对应的配置类变成对应的beanDefinition
 * --ConfigurationClassPostProcessor 实现属于BeanFactoryPostProcessor的postProcessBeanFactory 注册了后置处理器ConfigurationClassPostProcessor.ImportAwareBeanPostProcessor类
 *
 *
 * 底层的后置处理器
 * 通过RootBeanDefinition注册 org.springframework.context.annotation.internalConfigurationAnnotationProcessor---->ConfigurationClassPostProcessor 用于解析配置类
 * 	ConfigurationClassPostProcessor.ImportAwareBeanPostProcessor内部类
 *通过RootBeanDefinition注册 org.springframework.context.annotation.internalAutowiredAnnotationProcessor---->AutowiredAnnotationBeanPostProcessor 用于解析@Autowire，@Value自动装配
 *
 *按需通过RootBeanDefinition注册  org.springframework.context.annotation.internalCommonAnnotationProcessor---->CommonAnnotationBeanPostProcessor ---解析支持JSR250,处理@Resource注解 @PostConstruct，@PreDestroy
 *
 *
 *
 * AnnotatedGenericBeanDefinition 配置类的bean
 *
 * 解决对应的依赖关系DefaultListableBeanFactory可是实现了 AutowireCapableBeanFactory 接口，专用来解决对应的自动注入依赖相关的
 * org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveDependency(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String, java.util.Set, org.springframework.beans.TypeConverter)
 *
 *
 */



}
