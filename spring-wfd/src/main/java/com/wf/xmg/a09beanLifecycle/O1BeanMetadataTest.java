package com.wf.xmg.a09beanLifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

// bean的配置元信息 BeanDefinition
@SuppressWarnings({"deprecation"})
public class O1BeanMetadataTest {

	/**
	 *  BeanDefinition 体系
	 *
	 * 1.从不同的资源处进行读取
	 *
	 * BeanDefinitionReader接口相关 (读取配置元信息，主要是从资源中进行加载 xml properties groovy)
	 * AnnotatedBeanDefinitionReader 类型 主要是处理对应的注解形式
	 *
	 * 2.将读取到的进行转换为不同类型的BeanDefinition
	 *
	 * BeanDefinition接口相关 不同的业务类型存在不同的beanDefinition
	 * AnnotatedBeanDefinition   AbstractBeanDefinition
	 *
	 * 3.将不同类型的BeanDefinition进行注册到不同的业务逻辑中去
	 *
	 * BeanDefinitionRegistry接口相关(注册 BeanDefinition)
	 * SimpleBeanDefinitionRegistry  是简单的实现，只是将对应的值放入map 和容器没有关系
	 * DefaultListableBeanFactory 在工厂的容器中，进行了带业务逻辑的实现
	 * GenericApplicationContext  实现了接口，但是真正的逻辑是委托了上下文中的容器DefaultListableBeanFactory
	 *
	 *
	 * 4.辅助相关类
	 * BeanDefinitionReaderUtils 工具类
	 * BeanNameGenerator 用于生成对应的BeanDefinition 的 Name
	 * BeanDefinitionRegistryPostProcessor 工具类
	 *
	 */

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 实例化基于 Properties 资源 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
		String location = "META-INF/a09/bean-metadata-09.properties";
		// 基于 ClassPath 加载 properties 资源
		Resource resource = new ClassPathResource(location);
		// 指定字符编码 UTF-8 properties 使用的是iso-8859-1 进行获取的 需要指定字符编码
		// EncodedResource encodedResource = new EncodedResource(resource);
		EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
		int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
		// 通过 Bean Id 和类型进行依赖查找
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);


		System.out.println("================================================");

		// 基于注解的 BeanDefinitionReader
		AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
		int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
		// 注册当前类（非 @Component class）
		annotatedBeanDefinitionReader.register(O1BeanMetadataTest.class);
		int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
		int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
		System.out.println("已加载 BeanDefinition 数量：" + beanDefinitionCount);
		// 普通的 Class 作为 Component 注册到 Spring IoC 容器后，通常 Bean 名称为 annotatedBeanDefinitionParsingDemo
		// Bean 名称生成来自于 BeanNameGenerator，注解实现 AnnotationBeanNameGenerator
		O1BeanMetadataTest demo = beanFactory.getBean("o1BeanMetadataTest",
				O1BeanMetadataTest.class);
		System.out.println(demo);
	}
}
