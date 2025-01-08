package com.wf.model.annBean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.UnsupportedEncodingException;

/**
 * @Desc : @Bean的处理大致逻辑
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
@ComponentScan(basePackages = {"com.wf.model.annBean"})
public class ApplicationContextAnnBeanTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

		// ApplicationContext等上下文中存放了一个DefaultListableBeanFactory容器对象
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContextAnnBeanTest.class);
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
		System.out.println(cat+" === "+cat.getCategory());

		Cat catStatic = (Cat) annotationConfigApplicationContext.getBean("catStatic");
		System.out.println(catStatic+" === "+catStatic.getCategory());

		ParameterBean parameterBean = (ParameterBean) annotationConfigApplicationContext.getBean("parameterBean");
		System.out.println(parameterBean);
    }

	/**
	 * 前提：通过AnnotationConfigApplicationContext(ApplicationContextAnnBeanTest.class)创建我们的引导类，此类会被进行加载解析成对应的BeanDefinition。
	 *
	 * 如何处理@Bean
	 *
	 * 通过BeanFactoryPostProcessor的后置处理器ConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry进行解析配置文件,
	 * 获取系统中存在的BeanDefinition,开始判断是否属于配置类，通过判断是否有这些信息，@Configuration @Component  @ComponentScan @Import @ImportResource @Bean 如果存在这些注解，都会被认为是配置类
	 *
	 * 将得到的配置类委派为ConfigurationClassParser进行解析处理，并转换成对应的ConfigurationClass实例(由BeanDefinition转换到ConfigurationClass)
	 * 在这里会处理配置类的@Component  @PropertySources @PropertySource  @ComponentScans @ComponentScan @Import @Bean 注解 有些是会进行循环递归处理
	 *
	 *  针对@Bean的处理 找出配置类中的标记了@Bean注解的方法，并将其封装成BeanMethod对象 然后将信息放置到配置类ConfigurationClass中的beanMethods属性中
	 *
	 *  处理完所有的配置类后，就委派给ConfigurationClassBeanDefinitionReader 进行ConfigurationClass转换成BeanDefinition
	 *
	 *  针对@Bean的处理 从ConfigurationClass中获取所有的BeanMethod，然后针对每一个BeanMethod，进行loadBeanDefinitionsForBeanMethod处理
	 *  判断是否条件注册，然后根据beanMethod解析出来的元信息(静态方法/实例方法)，进行创建并填充DeanDefinition(factoryBeanName /factoryMethodName属性)，最后再进行注册到beanDefinitionRegistry中
	 *  创建的是 org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.ConfigurationClassBeanDefinition
	 *
	 *
	 * 然后再创建对应的对象的实例时，会通过instantiateUsingFactoryMethod()进行创建，因为@Bean的BeanDefinition的factoryMethodName属性不为空
	 *
	 *
	 *
	 *
	 *
	 *
	 */




}
