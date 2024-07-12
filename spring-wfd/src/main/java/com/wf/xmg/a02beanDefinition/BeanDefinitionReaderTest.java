package com.wf.xmg.a02beanDefinition;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

//02 我们怎么从资源中进行获取我们的BeanDefinition
public class BeanDefinitionReaderTest {
	/**
	 *  BeanDefinitionReade体系 用于获取对应的BeanDefinition信息
	 *  具体的bean定义读取器当然可以为bean定义添加额外的加载和注册方法，具体到它们的bean定义格式
	 *  AbstractBeanDefinitionReader 抽象类，实现了 BeanDefinitionReader接口， 并内置了一个BeanNameGenerator，用于生产bean名称
	 *  三个实现类,用于从不同的资源中读取beanDefinition
	 *  	PropertiesBeanDefinitionReader
	 *  	GroovyBeanDefinitionReader
	 *  	XmlBeanDefinitionReader
	 *  后来应该是BeanDefinitionReade顶层接口定义不完善，2.5新出的 AnnotatedBeanDefinitionReader 和 ClassPathBeanDefinitionScanner 并没有纳入BeanDefinitionReade体系
	 *
	 *
	 *
	 *  BeanNameGenerator体系 用于生成对应bean名称的接口
	 *  	两个实现类，DefaultBeanNameGenerator 和  AnnotationBeanNameGenerator 2.5出现
	 *
	 *
	 *
	 *  BeanDefinitionReaderUtils 用于处理在生成beanDefinition时的工具类
	 *
	 **/
	public static void main(String[] args) {


		// 创建 BeanFactory 容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		// XML 配置文件 ClassPath 路径
		String location = "classpath:/META-INF/bean-definition-reader.xml";

		// 加载配置
		int beanDefinitionsCount = reader.loadBeanDefinitions(location);

		System.out.println("Bean 定义加载的数量：" + beanDefinitionsCount);
		// 获取之后之后进行读取
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		System.out.println("容器中一共存在beanDefinitionName："+ Arrays.toString(beanDefinitionNames));

		// 从容器中得到加载的对象  为什么load之后就可以获取到bean实例呢？我们的refresh在哪里调用的呢？
		//上面的这些流程其实是我们自己利用Spring相关类进行了我们自己的的工厂使用
		//其实我们容器在getBean的时候才会创建bean，我们自定义的实例流程中并没有进spring上下文给我们提供的功能多。 在此处添加断点可以看到 beanFactory的SingletonObjects没有任何实例bean
		// 前面讲过 在容器中，我们的bean是懒加载模式，在get的时候才会进行实例化，针对与上下文中，则是会提前进行加载完成
		Student student = beanFactory.getBean("student", Student.class);
		System.out.println(student);
	}

}
