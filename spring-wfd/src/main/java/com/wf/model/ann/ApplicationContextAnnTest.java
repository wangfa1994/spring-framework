package com.wf.model.ann;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @Desc : 简单的上下文ApplicationContext的使用
 * @Author : Mr.WangF
 * @Date: 2022/7/20 10:41
 */
@ComponentScan(basePackages = {"com.wf.model.ann"})
public class ApplicationContextAnnTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("hello word");

		// ApplicationContext等上下文中存放了一个DefaultListableBeanFactory对象
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationContextAnnTest.class);

		// 在上下文中，上下文逻辑会帮我们预先创建出我们的bean,在获取的时候，直接进行得到即可
		Cat cat = (Cat) annotationConfigApplicationContext.getBean("cat");
		System.out.println(cat);
		Person person = (Person) annotationConfigApplicationContext.getBean("person");
		Cat cat1 = person.getCat();
		System.out.println(cat1);

    }

	/**存在的体系
	 * {@link BeanDefinition }体系:三种类型的BeanDefinition
	 * {@link org.springframework.beans.factory.support.BeanDefinitionReader}体系:BeanDefinition的解析器，包括两类，从资源中读取和从注解中读取
	 * {@link org.springframework.beans.factory.support.BeanNameGenerator}体系：生成BeanDefinition的名称，包括资源解析的命名规则，和注解类的命名规则
	 * {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}体系：BeanDefinition的注册体系，通过此接口，将我们的BeanDefinition注册到容器中
	 *
	 * {@link org.springframework.context.annotation.ClassPathBeanDefinitionScanner}体系：从classPath中获取我们的beanDefinition，会解析一些注解标注的类和注解同源
	 * {@link org.springframework.context.annotation.ScopeMetadataResolver}体系:用来解决对应的beanDefinition的作用域元信息
	 * {@link org.springframework.context.annotation.ConfigurationClassParser}:解析我们的配置文件
	 * {@link org.springframework.context.annotation.ComponentScanAnnotationParser}：解析我们的@ComponentScan注解
	 * {@link org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider} : 从指定的基本包开始扫描候选组件的组件
	 *
	 * {@link org.springframework.beans.factory.config.RuntimeBeanReference}:用来解决封装依赖的容器对象
	 *
	 * {@link org.springframework.beans.factory.support.AutowireCandidateResolver}体系
	 *
	 * {@link org.springframework.beans.factory.BeanFactory}体系：就是我们的spring容器，只包括我们的容器功能，解析BeanDefinition,创建对应的bean，预留扩展接口
	 * {@link org.springframework.context.ApplicationContext}体系：针对容器做的应用级别的上下文，实现一些扩展接口来处理我们的应用级别的功能。
	 *
	 *
	 *
	 * ConfigurationClassBeanDefinitionReader：
	 *
	 * ConfigurationClassParser 与 ConfigurationClassPostProcessor 类
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
	 * 应用上下文中的beanFactory的处理器， 默认的只有这一个BeanDefinitionRegistryPostProcessor，用来解析配置所有的beanDefinition的
	 * ConfigurationClassPostProcessor --> BeanDefinitionRegistryPostProcessor--> BeanFactoryPostProcessor
	 * ConfigurationClassPostProcessor：解析我们的配置文件，这个在应用上下文中的beanFactory实例化之后，就可是注册我们的BeanDefinition了。解析我们的配置类，将我们的配置类解析成对应的BeanDefinition
	 *
	 * BeanFactoryPostProcessor 也有一个EventListenerMethodProcessor类，是和事件监听相关的类
	 *
	 *
	 * InstantiationAwareBeanPostProcessor 与  BeanPostProcessor
	 *
	 * InstantiationAwareBeanPostProcessor 主要用于 属性赋值阶段， 里面的方法主要是针对Instantiation 实例化之后的操作和赋值
	 *
	 * BeanPostProcessor 里面的方法主要用于Initialization阶段，这时属性关系已经依赖解决，再进行一些依赖关系处理好的一些自定义属性替换，我必须默认的给定之后，才能允许你进行相关修改，不然的话，你的自定义修改有可能又被我给默认了
	 *
	 *
	 *
	 * 先想办法得到我们工厂，对我们的工厂进行默认能力扩展，然后在通过工厂后置处理对我们工厂再次进行扩展，扩展完我们的工厂之后，开始针对工厂里要生产对象的工具进行后置处理器扩展，扩展好之后，开始利用工厂创建对象
	 *
	 */

}
