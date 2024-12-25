package com.wf.model.ymlProperties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.UnsupportedEncodingException;

/**
 * @Desc : 简单的上下文ApplicationContext的使用
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
@ComponentScan(basePackages = {"com.wf.model.ymlProperties"})
@PropertySource({"/model/configPro.yml"})
public class ApplicationContextConfigProTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

		// ApplicationContext等上下文中存放了一个DefaultListableBeanFactory对象
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfigProTest.class);

		String property = annotationConfigApplicationContext.getEnvironment().getProperty("system_pause.switchFlag");

		// 在上下文中，上下文逻辑会帮我们预先创建出我们的bean,在获取的时候，直接进行得到即可
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
		System.out.println(cat);


    }

	/**存在的体系
	 * BeanDefinition体系:三种类型的BeanDefinition
	 * BeanDefinitionReader体系:BeanDefinition的解析器，包括两类，从资源中读取和从注解中读取
	 * ClassPathBeanDefinitionScanner体系：从classPath中获取我们的beanDefinition，会解析一些注解标注的类和注解同源
	 * BeanNameGenerator体系：生成BeanDefinition的名称，包括资源解析的命名规则，和注解类的命名规则
	 * ScopeMetadataResolver体系:用来解决对应的beanDefinition的作用域元信息
	 * BeanDefinitionRegistry体系：BeanDefinition的注册体系，通过此接口，将我们的BeanDefinition注册到容器中
	 *
	 * RuntimeBeanReference:用来解决封装依赖的容器对象
	 *
	 * AutowireCandidateResolver体系
	 *
	 * BeanFactory体系：就是我们的spring容器，只包括我们的容器功能，解析BeanDefinition,创建对应的bean，预留扩展接口
	 * ApplicationContext体系：针对容器做的应用级别的上下文，实现一些扩展接口来处理我们的应用级别的功能。
	 *
	 * ConfigurationClassParser:解析我们的配置文件
	 * ComponentScanAnnotationParser：解析我们的@ComponentScan注解
	 * ClassPathBeanDefinitionScanner 、ClassPathScanningCandidateComponentProvider
	 * ConfigurationClassBeanDefinitionReader：
	 *
	 * InstantiationStrategy体系用于创建对应的bean实例
	 * SingletonBeanRegistry体系：Bean实例对象的注册体系。直接将我们的bean实例对象注册到我们的容器中
	 * BeanWrapper体系，包装已经创建好的bean实例
	 *
	 *
	 * TypeConverter:赋值过程中出现的类型转换器？
	 * BeanDefinitionValueResolver :
	 * BeanReference ,在解决属性依赖的时候，会封装成不同类型的BeanReference  有不同的实现来处理不同情况下的依赖
	 *
	 *
	 * Resource : spring的资源体系，是java的资源体系的扩展
	 * ResourceLoader 资源加载器，
	 *
	 *
	 * AnnotationConfigUtils
	 * ClassUtils
	 * BeanDefinitionReaderUtils：BD的读取工具类，创建，起名，注册功能
	 * BeanUtils
	 * ResourcePatternUtils: 资源解析相关的工具类
	 * BeanFactoryUtils
	 *
	 * 原生的beanFactory是没有对应的bean的后置处理器的，只是简单的帮我们进行创建对象，管理对象，解决对象间的依赖关系，
	 *
	 * ParameterNameDiscoverer参数解析体系
	 *
	 * 应用上下文中的beanFactory的处理器，
	 * ConfigurationClassPostProcessor --> BeanDefinitionRegistryPostProcessor--> BeanFactoryPostProcessor
	 * ConfigurationClassPostProcessor：解析我们的配置文件，这个在应用上下文中的beanFactory实例化之后，就可是注册我们的BeanDefinition了。解析我们的配置类，将我们的配置类解析成对应的BeanDefinition
	 *
	 */

}
